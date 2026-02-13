package com.Analyser.Entity;

import com.Analyser.ENUM.MobileAttackType;

public class IOSLog extends BaseLog{
    private String month;
    private int day;
    private String time;
    private String device;
    private String process;

    public IOSLog(String month, int day, String time, String device, String process, int pid, String level, String message, MobileAttackType attackType) {
        this.month = month;
        this.day = day;
        this.time = time;
        this.device = device;
        this.process = process;
        this.pid = pid;
        this.level = level;
        this.message = message;
        this.attackType = attackType;
    }

    public IOSLog() {
    }

    @Override
    public String toString() {
        return "IOSLog{" +
                "month='" + month + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", device='" + device + '\'' +
                ", process='" + process + '\'' +
                ", pid=" + pid +
                ", level='" + level + '\'' +
                ", message='" + message + '\'' +
                ", attackType=" + attackType +
                '}';
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MobileAttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(MobileAttackType attackType) {
        this.attackType = attackType;
    }

    private int pid;
    private String level;
    private String message;

    private MobileAttackType attackType;
}
