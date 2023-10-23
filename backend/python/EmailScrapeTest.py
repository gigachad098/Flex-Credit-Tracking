import os
import email
import ssl
import smtplib
import imaplib
from cleantext import clean
import codecs

user_email = "tommy.tpg03@gmail.com"
email_password = os.environ.get('EMAIL-PASS')
email_receiver = "malvarez2022@my.fit.edu"

subject = "Testing Auto Email Sender"
body = "cool beans :)"

em = email.message.EmailMessage()
em['From'] = user_email
em['To'] = email_receiver
em['Subject'] = subject
em.set_content(body)

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
    
    print(f"\n{messages} messages\n")
    
    TOTAL_MESSAGES = 15
    
    for i in range(messages, messages - TOTAL_MESSAGES, -1):
        res, msg = imap.fetch(str(i), "(RFC822)")
        for response in msg:
            if isinstance(response, tuple):
                # parse a bytes email into a message object
                msg = email.message_from_bytes(response[1], policy=email.policy.default)
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
                
                print(f"{From.split('<')[0].strip()}: ", end="")
                
                subject = clean(subject.strip(), no_emoji=True)
                
                if len(subject) == 0:
                    print("(no subject)")
                elif len(subject) > 60:
                    print(f"{subject[:57]}...")
                else:
                    print(f"{subject}")
