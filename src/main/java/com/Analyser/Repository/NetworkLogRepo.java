package com.Analyser.Repository;


import com.Analyser.Entity.NetworkLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class NetworkLogRepo {
    private MongoCollection<NetworkLog> collection;

    public NetworkLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("network_logs", NetworkLog.class);
    }

    public void save(NetworkLog log) {
        collection.insertOne(log);
    }
}
