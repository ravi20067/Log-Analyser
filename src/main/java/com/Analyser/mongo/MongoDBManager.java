package com.Analyser.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.*;

public class MongoDBManager {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "logSecurityDB";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new com.mongodb.ConnectionString(CONNECTION_STRING))
                .codecRegistry(pojoCodecRegistry)
                .build();

        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);

        System.out.println("✅ MongoDB Connected Successfully");
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
