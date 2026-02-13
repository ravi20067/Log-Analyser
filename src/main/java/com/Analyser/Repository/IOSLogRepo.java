package com.Analyser.Repository;

import com.Analyser.ENUM.MobileAttackType;
import com.Analyser.Entity.IOSLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.List;

public class IOSLogRepo {

    private final MongoCollection<IOSLog> collection;

    public IOSLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        this.collection = database.getCollection("ios_logs", IOSLog.class);
    }

    // ✅ Save single log
    public void save(IOSLog log) {
        collection.insertOne(log);
    }

    // ✅ Save multiple logs (Batch insert - Recommended)
    public void saveAll(List<IOSLog> logs) {
        if (logs != null && !logs.isEmpty()) {
            collection.insertMany(logs);
        }
    }

    // ✅ Find all logs
    public List<IOSLog> findAll() {
        return collection.find().into(new java.util.ArrayList<>());
    }

    // ✅ Find by attack type
    public List<IOSLog> findByAttackType(MobileAttackType attackType) {
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

