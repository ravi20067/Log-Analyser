package com.Analyser.Repository;

import com.Analyser.ENUM.MobileAttackType;
import com.Analyser.Entity.AndroidLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.List;

public class AndroidLogRepo {
    private MongoCollection<AndroidLog> collection;

    public AndroidLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("android_log", AndroidLog.class);
    }

    public void save(AndroidLog log) {
        collection.insertOne(log);
    }

    // ✅ Save multiple logs (Batch insert - Recommended)
    public void saveAll(List<AndroidLog> logs) {
        if (logs != null && !logs.isEmpty()) {
            collection.insertMany(logs);
        }
    }

    // ✅ Find all logs
    public List<AndroidLog> findAll() {
        return collection.find().into(new java.util.ArrayList<>());
    }

    // ✅ Find by attack type
    public List<AndroidLog> findByAttackType(MobileAttackType attackType) {
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
