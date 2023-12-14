import org.bson.Document;

public class User {
    static String DEFAULT_PASSWORD = "";
    private final long userID;
    private final String password;

    // make a user with a specific user ID
    public User (long uid, String pw) {
        userID = uid;
        password = pw;
    }

    // check password without sending it to external source
    public boolean checkPassword (String pword) {
        return pword.equals(password);
    }

    // getter for password
    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "User " + this.userID;
    }


    // Convert between User object and Document; Important
    public static User fromDoc(Document doc) {
        try {
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
