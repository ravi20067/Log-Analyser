package com.Analyser.Repository;

import com.Analyser.Entity.LinuxLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class LinuxLogRepo {
    private MongoCollection<LinuxLog> collection;

    public LinuxLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("linux_logs", LinuxLog.class);
    }

    public void save(LinuxLog log) {
        collection.insertOne(log);
    }
}
