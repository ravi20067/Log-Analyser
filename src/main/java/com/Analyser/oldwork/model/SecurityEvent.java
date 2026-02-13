package com.Analyser.oldwork.model;


import com.Analyser.oldwork.detect.LogType;

public class SecurityEvent {

    public String timestamp;
    public String sourceIp;
    public String username;
    public String action;
    public String resource;
    public int statusCode;
    public LogType sourceType;
    public String raw;

    @Override
    public String toString() {
        return "[" + sourceType + "] " + action + " | " + resource +
                " | IP=" + sourceIp + " | USER=" + username;
    }
}
