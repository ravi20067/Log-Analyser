package com.Analyser.Repository;

import com.Analyser.Entity.WebLog;
import com.Analyser.mongo.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class WebLogRepo {
    private MongoCollection<WebLog> collection;

    public WebLogRepo() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("web_logs", WebLog.class);
    }

    public void save(WebLog log) {
        collection.insertOne(log);
    }
}
