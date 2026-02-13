package com.Analyser.Entity;

import com.Analyser.ENUM.WebAttackType;

public class WebLog extends BaseLog{

    private String ip;
    private String timestamp;
    private String method;
    private String url;
    private String protocol;
    private int statusCode;
    private int responseSize;
    private String referrer;
    private String userAgent;

    private WebAttackType attackType;  // extra field

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }

    public WebLog(String ip, String timestamp, String method,
                  String url, String protocol,
                  int statusCode, int responseSize,
                  String referrer, String userAgent , WebAttackType type) {

        this.ip = ip;
        this.timestamp = timestamp;
        this.method = method;
        this.url = url;
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.responseSize = responseSize;
        this.referrer = referrer;
        this.userAgent = userAgent;

        this.attackType = type;
    }

    // Getter + Setter for attackType
    public WebAttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(WebAttackType attackType) {
        this.attackType = attackType;
    }

    @Override
    public String toString() {
        return "WebLog{" +
                "ip='" + ip + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", statusCode=" + statusCode +
                ", responseSize=" + responseSize +
                ", referrer='" + referrer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", attackType=" + attackType +
                '}';
    }
}


