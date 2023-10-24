This folder contains the implementation of the MongoDB connectivity for the FLEX Credit Tracker project.
The User class defines a User object that stores a user ID and password,
as well as methods to convert between User objects and database Document objects.
The Database class contains methods to access the database and create, get, and delete users.
It also has a Login method to log in to the database. Until we can find a better
way to log in without hardcoding the username/password, this method will take input from the standard input stream.
Finally, the pom.xml file includes everything that is needed to access the database with Maven.