package com.Analyser.Entity;

import com.Analyser.ENUM.LinuxAttackType;

public class LinuxLog extends BaseLog {
    private String date;        // Feb 15
    private String time;        // 13:55:01
    private String host;        // webserver
    private String service;     // sshd[5021]
    private String message;     // full log message

    private String sourceIp;
    private String username;
    private String process;
    private String filePath;

    private LinuxAttackType attackType;

    public LinuxLog() {}

    public LinuxLog(String date, String time, String host,
                    String service, String message,
                    String sourceIp, String username,
                    String process, String filePath,
                    LinuxAttackType attackType) {

        this.date = date;
        this.time = time;
        this.host = host;
        this.service = service;
        this.message = message;
        this.sourceIp = sourceIp;
        this.username = username;
        this.process = process;
        this.filePath = filePath;
        this.attackType = attackType;
    }

    @Override
    public String toString() {
        return "LinuxLog{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", host='" + host + '\'' +
                ", service='" + service + '\'' +
                ", message='" + message + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", username='" + username + '\'' +
                ", process='" + process + '\'' +
                ", filePath='" + filePath + '\'' +
                ", attackType=" + attackType +
                '}';
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getSourceIp() { return sourceIp; }
    public void setSourceIp(String sourceIp) { this.sourceIp = sourceIp; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getProcess() { return process; }
    public void setProcess(String process) { this.process = process; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public LinuxAttackType getAttackType() { return attackType; }
    public void setAttackType(LinuxAttackType attackType) { this.attackType = attackType; }

}
