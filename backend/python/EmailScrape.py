import email
import ssl
import imaplib
import subprocess
import os


user_email = "flexcredittracker@gmail.com"
    
batcmd="seed.exe"
response = subprocess.check_output(batcmd).decode("utf-8")
seed = response[15:31][::-1]

context = ssl.create_default_context()

with imaplib.IMAP4_SSL('imap.gmail.com', ssl_context=context) as imap:
    imap.login(user_email, seed)
    status, messages = imap.select("INBOX")
    
    messages = int(messages[0])
    
    for i in range(messages, messages - 10, -1):
        res, msg = imap.fetch(str(i), "(RFC822)")
        for response in msg:
            if isinstance(response, tuple):
                # parse a bytes email into a message object
                msg = email.message_from_bytes(response[1])
                # decode the email subject
                subject, encoding = email.header.decode_header(msg["Subject"])[0]
                
                if isinstance(subject, bytes) and isinstance(encoding, str):
                    # if it's a bytes, decode to str
                    subject = subject.decode(encoding)
                # decode email sender
                From, encoding = email.header.decode_header(msg.get("From"))[0]
                if isinstance(From, bytes):
                    From = From.decode(encoding)
                
                from_string = f"{From.split('<')[0].strip()}"
                if from_string != "Panther ACCESS Card Accounts":
                    continue
                
                
                for part in msg.walk():
                    if part.get_content_maintype() == 'multipart':
                        continue
                    temp = part.get_payload(decode=True).decode('utf-8')
                    password_index = temp.find("Password: ")
                    last_name_index = temp.find("Name: ")
                    password_end_index = temp[password_index:].find('\r\n') + password_index
                    last_name_end_index = temp[last_name_index:].find('\r\n') + last_name_index
                    password = temp[password_index + 10:password_end_index].strip()
                    last_name = temp[last_name_index + 6:last_name_end_index].strip().split(" ")[1].lower()
                    print(password, last_name)
                    break
