package be.unamur.typhon.datagenerator.nosql;

import be.unamur.typhon.datagenerator.generator.DataGenerator;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Main extends DataGenerator {

    public static MongoClientURI connectionString = new MongoClientURI("mongodb://admin:admin@localhost:27018");

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("DocumentDatabase");
        MongoCollection<Document> ordersCollection = database.getCollection("Order");
        List<Document> newOrders = new ArrayList<>();
        for (int counter = 0; counter < 1000; counter++) {
            Document order = new Document()
                    .append("_id", generateUuid())
                    .append("id", generateRandomString())
                    .append("number", generateRandomInt())
                    .append("price", generateRandomString())
                    .append("quantity", generateRandomString());
            newOrders.add(order);
        }
        ordersCollection.insertMany(newOrders);
    }
}
