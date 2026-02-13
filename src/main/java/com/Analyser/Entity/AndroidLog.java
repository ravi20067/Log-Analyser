package com.Analyser.Entity;

import com.Analyser.ENUM.MobileAttackType;

public class AndroidLog extends BaseLog{
    private String date;
    private String time;
    private int pid;
    private int tid;
    private String level;   // I, W, E
    private String tag;     // OkHttp, SecurityMonitor etc
    private String message;

    @Override
    public String toString() {
        return "AndroidLog{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", pid=" + pid +
                ", tid=" + tid +
                ", level='" + level + '\'' +
                ", tag='" + tag + '\'' +
                ", message='" + message + '\'' +
                ", attackType=" + attackType +
                '}';
    }

    public AndroidLog(String date, String level, int pid, String time, int tid, String tag, String message, MobileAttackType attackType) {
        this.date = date;
        this.level = level;
        this.pid = pid;
        this.time = time;
        this.tid = tid;
        this.tag = tag;
        this.message = message;
        this.attackType = attackType;
    }

    public AndroidLog() {
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public MobileAttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(MobileAttackType attackType) {
        this.attackType = attackType;
    }

    private MobileAttackType attackType;
}
