package com.Analyser.Entity;

import com.Analyser.ENUM.NetworkAttackType;
import org.bson.types.ObjectId;

public class NetworkLog extends BaseLog{

    private String date;
    private String time;
    private String device;
    private String service;
    private String message;

    private String sourceIp;
    private String destinationIp;
    private String protocol;
    private String sourcePort;
    private String destinationPort;

    private NetworkAttackType attackType;

    public NetworkLog() {}

    public NetworkLog(String date, String time, String device,
                      String service, String message,
                      String sourceIp, String destinationIp,
                      String protocol, String sourcePort,
                      String destinationPort,
                      NetworkAttackType attackType) {

        this.date = date;
        this.time = time;
        this.device = device;
        this.service = service;
        this.message = message;
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
        this.protocol = protocol;
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.attackType = attackType;
    }

    @Override
    public String toString() {
        return "NetworkLog{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", device='" + device + '\'' +
                ", service='" + service + '\'' +
                ", message='" + message + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", destinationIp='" + destinationIp + '\'' +
                ", protocol='" + protocol + '\'' +
                ", sourcePort='" + sourcePort + '\'' +
                ", destinationPort='" + destinationPort + '\'' +
                ", attackType=" + attackType +
                '}';
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public NetworkAttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(NetworkAttackType attackType) {
        this.attackType = attackType;
    }
// getters & setters (required for Mongo POJO)
}

