package com.Analyser.Repository;

import com.Analyser.ENUM.NetworkAttackType;
import com.Analyser.Entity.NetworkLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.List;

public class NetworkLogRepo {
    private MongoCollection<NetworkLog> collection;

    public NetworkLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("network_logs", NetworkLog.class);
    }

    public void save(NetworkLog log) {
        collection.insertOne(log);
    }

    // ✅ Save multiple logs (Batch insert - Recommended)
    public void saveAll(List<NetworkLog> logs) {
        if (logs != null && !logs.isEmpty()) {
            collection.insertMany(logs);
        }
    }

    // ✅ Find all logs
    public List<NetworkLog> findAll() {
        return collection.find().into(new java.util.ArrayList<>());
    }

    // ✅ Find by attack type
    public List<NetworkLog> findByAttackType(NetworkAttackType attackType) {
        return collection.find(
                Filters.eq("attackType", attackType)
        ).into(new java.util.ArrayList<>());
    }

    // ✅ Count logs
    public long count() {
        return collection.countDocuments();
    }

    // ✅ Delete all logs (for testing)
    public void deleteAll() {
        collection.deleteMany(new org.bson.Document());
    }
}
