package com.Analyser.Repository;

import com.Analyser.Entity.AndroidLog;
import com.Analyser.Entity.WebLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AndroidLogRepo {
    private MongoCollection<AndroidLog> collection;

    public AndroidLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("android_log", AndroidLog.class);
    }

    public void save(AndroidLog log) {
        collection.insertOne(log);
    }
}
