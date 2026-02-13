package com.Analyser.Entity;
import org.bson.types.ObjectId;

public abstract class BaseLog {

    private ObjectId id;   // Mongo auto _id
    private String ip;
    private String timestamp;
    private String sourceType;

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
}

