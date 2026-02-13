package com.Analyser.Repository;

import com.Analyser.ENUM.LinuxAttackType;
import com.Analyser.Entity.LinuxLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.List;

public class LinuxLogRepo {
    private MongoCollection<LinuxLog> collection;

    public LinuxLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("linux_logs", LinuxLog.class);
    }

    public void save(LinuxLog log) {
        collection.insertOne(log);
    }

    // ✅ Save multiple logs (Batch insert - Recommended)
    public void saveAll(List<LinuxLog> logs) {
        if (logs != null && !logs.isEmpty()) {
            collection.insertMany(logs);
        }
    }

    // ✅ Find all logs
    public List<LinuxLog> findAll() {
        return collection.find().into(new java.util.ArrayList<>());
    }

    // ✅ Find by attack type
    public List<LinuxLog> findByAttackType(LinuxAttackType attackType) {
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
