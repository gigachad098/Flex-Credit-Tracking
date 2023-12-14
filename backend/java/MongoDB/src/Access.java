import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

public class Access {
    static final int MAX_ACCESS_ATTEMPTS = 3;
    static final String scrapeEmailCommand = "python ..\\..\\..\\python\\EmailScrape.py";
    static final String csvFetchCommand = "node ..\\..\\..\\..\\frontend\\scrape.mjs ";
    static final String statAnalysisCommand = "python ..\\..\\..\\python\\StatAnalysis.py ..\\src";
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Pass the user ID and (optionally) a last name as an argument.");
            return;
        }
        try{
            long userID = Long.parseLong(args[0]);
            String lastName = null;
            if (args.length > 1) {
                lastName = args[1].toLowerCase();
            }
            accessDatabase(userID, lastName);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("Please enter a valid User ID");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static void accessDatabase (long userID, String lastName) {

        for (int i = 0; i < MAX_ACCESS_ATTEMPTS; i++) {
            if (Database.isInDatabase(userID)){
                String password = Database.getPassword(userID);
                accessData(password);
                return;
            } else if (lastName != null) {
                createAccount(userID, lastName);
            }
        }
        System.out.println("ERROR:\tUnable to access database");
    }

    public static void createAccount(long userID, String lastName) {
        // Assumes the email has been sent
        // First half of Main: Scrape email
        
        ArrayList<String> output = new ArrayList<>();

        //System.out.println("Scraping email...\n");
        for (int i = 0; i < MAX_ACCESS_ATTEMPTS; i++) {
            output = TerminalHandler.execute(scrapeEmailCommand);
            if (output.size() > 0) {
                for (String s : output) {
                    String[] tokens = s.trim().split(" ");
                    if (tokens.length < 2) { continue; }
                    String tempPass = tokens[0];
                    String lastNameCheck = tokens[1];
                    if (lastNameCheck.toLowerCase().equals(lastName)) {
                        Database.addUser(userID, tempPass);
                        return;
                    }
                }
            }
        }
        System.out.println("ERROR:\tUnable to create new user");

    }

    public static void accessData(String password) {

        // same as the second half of Main
        // May change this to work with the UI
        ArrayList<String> output = new ArrayList<String>();
        //System.out.println("Fetching data...");

        for (int i = 0; i < MAX_ACCESS_ATTEMPTS; i++) {
            TerminalHandler.execute(csvFetchCommand + password);
            output = TerminalHandler.execute(statAnalysisCommand);
            if (output.get(0).equals("Try again Java!")) {
                //System.out.println("Unable to fetch data... trying again...");
                output.clear();
                continue;
            }
            //System.out.println("\n***START OF ANALYZED DATA***");
            for (String s : output) {
                System.out.println(s);
            }

            return;
        }
        System.out.println("ERROR:\tUnable to download spending data");

    }
}