import sys
from datetime import datetime, timedelta
from os import listdir, remove
from os.path import isfile, join

LOCATIONS = {"Panther Grocery 74" : "Panther Grocery",
             "Panther Grocery 74 - Purchase" : "Panther Grocery",
             "Panther Grocery 72" : "Panther Grocery",
             "UGRYD 5900 Vending" : "Vending Machine",
             "Rat Main Line 83" : "The Rathskeller",
             "AERO Einstein Bagels 92" : "Einstein Bagels",
             "Center Court 76" : "Clemente Center Court",
             "Panther Dining 70 WS6" : "Panther Dining Hall",
             "Panther Dining 71 WS6" : "Panther Dining Hall",
             "PatronImport Location - Credit" : "Deposit Funds"}



def valid_args():
    
    if len(sys.argv) != 2:
        print(f"ERROR:\t\"StatAnalysis.py\" recieved incorrect number " + 
                f"of arguments (expected 1, got {len(sys.argv) - 1})")
        return False, ""

    filepath = sys.argv[1]
    
    onlyfiles = [f for f in listdir(filepath) if isfile(join(filepath, f))]
    
    found = False
    for f in onlyfiles:
        if f[-4:] == ".csv":
            if found:
                print(f"ERROR:\t\"StatAnalysis.py\" recieved a file path " +
                        f"containing multiple .csv files")
                return False, ""
            filename = f
            found = True

    if not found:
        print(f"Try again Java!")
        return False, ""
    
    return True, join(filepath, filename)



def today_spend_stats(filename):
    file = open(filename, "r")
    today = datetime.today().strftime("%m/%d/%Y")
    total_today = 0
    amount_left = -1
    
    for line in file:
        tokens = line.strip().split(",")
        if tokens[0] == "Date":
            continue
        date = tokens[0].split()[0]
        amount =  -float(tokens[2])
        if amount < 0:
            if amount_left == -1:
                amount_left = float(tokens[3])
            continue
        if date == today:
            total_today += amount
            if amount_left == -1:
                amount_left = float(tokens[3])
        else:
            break
    return total_today, amount_left
    



def semester_spend_stats(filename):
    file = open(filename, "r")
    semester_amount = 0
    first_spend_day = ""
    prev_day = ""
    
    for line in file:
        tokens = line.strip().split(",")
        if tokens[0] == "Date":
            continue
        date = tokens[0].split()[0]
        amount = -float(tokens[2])
        purchase_balance = float(tokens[3])
        # First import of semester
        if tokens[1].find("PatronImport") == 0 or tokens[1].find("JSA") == -1 and amount < 0:
            semester_amount += purchase_balance
            break
        # Handle deposits
        if amount < 0:
            semester_amount -= amount
            continue
        # Track first spend day (roughly start of semester)
        if date != prev_day:
            if amount > 0:
                first_spend_day = date
            prev_day = date
            
    return semester_amount, first_spend_day




def purchases_per_location(filename):
    file = open(filename, "r")

    location_purchases = dict()

    for line in file:
        tokens = line.strip().split(",")
        if tokens[0] == "Date":
            continue
        if LOCATIONS.get(tokens[1]) == None:
            location = "Unknown"
        else:
            location = LOCATIONS[tokens[1]]
        
        if location == "Deposit Funds":
            continue
        
        if location in location_purchases.keys():
            location_purchases[location] += 1
        else:
            location_purchases[location] = 1

    # Extract locations-frequency pairs from dictionary and rank locations by frequency
    ranked_locations = [y[::-1] for y in sorted([x[::-1] for x in location_purchases.items()])][::-1]
    return ranked_locations



def semester_day_info(first_spend_day, days_in_semester):
    last_semester_day = first_spend_day + timedelta(days=days_in_semester)
    days_so_far = (datetime.today() - first_spend_day).days
    days_left = days_in_semester - days_so_far
    return last_semester_day, days_so_far, days_left



def main():
    
    valid, filename = valid_args()
    
    if not valid:
        return
    
    total_days = 119
    
    ranked_locations = purchases_per_location(filename)
    starting_amount, first_spend_day = semester_spend_stats(filename)
    spent_today, amount_remaining = today_spend_stats(filename)
    total_spent = starting_amount - amount_remaining
    
    month = int(first_spend_day.split("/")[0])
    if month > 4 and month < 7:
        total_days = 7*9
    
    first_spend_day = datetime.strptime(first_spend_day, "%m/%d/%Y")
    last_semester_day, days_so_far, days_left = semester_day_info(first_spend_day, total_days)
    
    ideal_average = starting_amount / total_days
    net = ideal_average * days_so_far - total_spent
    net_spending_string = f"-${-net:.2f}" if net < 0 else f"${net:.2f}"
    
    # -------------------------------------------------------
    #   Below is just a formatted print of all of the data
    # -------------------------------------------------------
    print()
    #print(f"{days_so_far} into the semester")
    #print(f"{days_left} days left\n")

    print(f"First spend day of current semester: {first_spend_day.strftime('%m/%d/%Y')}")
    print(f"Approximate end of semester: {last_semester_day.strftime('%m/%d/%Y')}\n")

    print(f"Flex this semester: ${starting_amount:.2f}")
    print(f"Total spent (so far): ${total_spent + spent_today:.2f}")
    print(f"Total spent (not including today): ${total_spent:.2f}")
    print(f"Amount remaining: ${amount_remaining:.2f}\n")
    
    print(f"Ideal daily spending: ${ideal_average:.2f}")
    print(f"Average daily spending (so far): ${total_spent / (days_so_far):.2f}")
    print(f"Allowed daily spending (after today): ${amount_remaining / days_left:.2f}")
    print(f"Net spending: {net_spending_string}\n")
    
    print(f"Money spent today (so far): ${spent_today:0.2f}\n")

    print(f"{'-'*50}\n{'Purchases':^50}\n{'-'*50}")
    for item in ranked_locations:
        location = item[0]
        purchases = item[1]
        if purchases == 1:
            print(f"{purchases} purchase at {location}")
        else:
            print(f"{purchases} purchases at {location}")
    # -------------------------------------------------------
    #                End of formatted print
    # -------------------------------------------------------
    
    #remove(filename)
            
        
      
        
main()