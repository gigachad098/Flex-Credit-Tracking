import os
import email
import ssl
import smtplib
import imaplib
from cleantext import clean
import codecs

user_email = os.environ.get('EMAIL-ADR')
email_password = os.environ.get('EMAIL-PASS')
email_receiver = "malvarez2022@my.fit.edu"

#print(user_email)
#print(email_password)

context = ssl.create_default_context()

'''
with smtplib.SMTP_SSL('smtp.gmail.com', 465, context=context) as smtp:
    smtp.login(user_email, email_password)
    smtp.sendmail(user_email, email_receiver, em.as_string())
'''

with imaplib.IMAP4_SSL('imap.gmail.com', ssl_context=context) as imap:
    imap.login(user_email, email_password)
    status, messages = imap.select("INBOX")
    
    messages = int(messages[0])
    
    #print(f"\n{messages} messages\n")
    
    
    for i in range(messages, 0, -1):
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
                    From = clean(From.strip(), no_emoji=True)
                
                from_string = f"{From.split('<')[0].strip()}"
                if from_string != "Panther ACCESS Card Accounts":
                    continue
                
                subject = clean(subject.strip(), no_emoji=True)
                
                for part in msg.walk():
                    if part.get_content_maintype() == 'multipart':
                        continue
                    temp = part.get_payload(decode=True).decode('utf-8')
                    password_index = temp.find("Password: ")
                    password_end_index = temp[password_index:].find('\r\n') + password_index
                    password = temp[password_index + 10:password_end_index].strip()
                    print(password)
                    break
