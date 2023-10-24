import org.bson.Document;

public class User {
    static String DEFAULT_PASSWORD = "";
    private long userID;
    private String password;

    // make a user with a specific user ID
    public User (long uid, String pw) {
        userID = uid;
        password = pw;
    }


    // setter for userID
    public boolean setUserID (long newID) {
        userID = newID;
        return true;
    }

    // check password without sending it to external source
    public boolean checkPassword (String pword) {
        return pword.equals(password);
    }

    // update password; Requires current password
    public boolean updatePassword (String pword, String newPword) {
        if (checkPassword(pword)) {
            password = newPword;
            return true;
        }
        return false;
    }

    // getter for password
    public String getPassword() {
        return this.password;
    }

    public long getID() {
        return this.userID;
    }

    public String toString() {
        return "User " + this.userID;
    }


    // Convert between User object and Document; Important
    public static User fromDoc(Document doc) {
        try {
            //String name = (String) doc.getOrDefault("name", DEFAULT_NAME);
            long userID = (long) doc.getOrDefault("userID", 0);
            String password = (String) doc.getOrDefault("password", DEFAULT_PASSWORD);
            return new User (userID, password);
        } catch (Exception e) {
            System.err.println(e);
            return new User(0, "");
        }
    }

    public Document toDoc() {
        Document doc = new Document();
        doc.put("userID", this.userID);
        doc.put("password", this.password);
        return doc;
    }
}
