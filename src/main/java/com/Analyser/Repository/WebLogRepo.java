package com.Analyser.Repository;

import com.Analyser.ENUM.WebAttackType;
import com.Analyser.Entity.WebLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.List;

public class WebLogRepo {
    private MongoCollection<WebLog> collection;

    public WebLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("web_logs", WebLog.class);
    }

    public void save(WebLog log) {
        collection.insertOne(log);
    }

    // ✅ Save multiple logs (Batch insert - Recommended)
    public void saveAll(List<WebLog> logs) {
        if (logs != null && !logs.isEmpty()) {
            collection.insertMany(logs);
        }
    }

    // ✅ Find all logs
    public List<WebLog> findAll() {
        return collection.find().into(new java.util.ArrayList<>());
    }

    // ✅ Find by attack type
    public List<WebLog> findByAttackType(WebAttackType attackType) {
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
