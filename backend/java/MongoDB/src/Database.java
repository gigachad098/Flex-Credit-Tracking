import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {

    public static boolean addUser(long userID, String password) {
        String url = login();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try (MongoClient mongoClient = MongoClients.create(url)){
            MongoDatabase database = mongoClient.getDatabase("Users_Test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> docs = database.getCollection("Users_Test_list");
            Document doc = new User(userID, password).toDoc();
            docs.insertOne(doc);
            return true;
        }
        catch (Exception e) {
            System.err.print("Error adding user");
            return false;
        }
    }

    public static String getPassword(long userID) {
        String url = login();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try (MongoClient mongoClient = MongoClients.create(url)){
            MongoDatabase database = mongoClient.getDatabase("Users_Test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> docs = database.getCollection("Users_Test_list");
            Document doc = docs.find(eq("userID", userID)).first();
            return User.fromDoc(doc).getPassword();
        }
        catch (Exception e) {
            System.err.print("Error retrieving password");
            return "";
        }
    }

    public static User getUser (long userID) {
        String url = login();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try (MongoClient mongoClient = MongoClients.create(url)){
            MongoDatabase database = mongoClient.getDatabase("Users_Test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> docs = database.getCollection("Users_Test_list");
            Document doc = docs.find(eq("userID", userID)).first();
            return User.fromDoc(doc);
        }
        catch (Exception e) {
            System.err.print("Error retrieving user");
            return null;
        }
    }

    public static User delete (long userID) {
        String url = login();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try (MongoClient mongoClient = MongoClients.create(url)){
            MongoDatabase database = mongoClient.getDatabase("Users_Test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> docs = database.getCollection("Users_Test_list");
            Document doc = docs.find(eq("userID", userID)).first();
            DeleteResult result = docs.deleteOne(eq("userID", userID));
            if (result.getDeletedCount() == 0) return null;
            return User.fromDoc(doc);
        }
        catch (Exception e) {
            System.err.print("Error retrieving user");
            return null;
        }
    }

    public static boolean isInDatabase(long userID) {
        String url = login();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try (MongoClient mongoClient = MongoClients.create(url)){
            MongoDatabase database = mongoClient.getDatabase("Users_Test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> docs = database.getCollection("Users_Test_list");
            Document doc = docs.find(eq("userID", userID)).first();
            return doc != null && doc.size() != 0;
        }
        catch (Exception e) {
            System.err.print("Error checking for user");
            return false;
        }
    }

    public static String login() {
        return Encrypt.makePassword(Username.getUsername(), Password.getPassword());
    }
}
