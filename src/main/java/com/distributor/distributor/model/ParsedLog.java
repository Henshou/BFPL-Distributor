package com.distributor.distributor.model;

import java.util.List;

public class ParsedLog {
    private String dateTime;
    private String username;
    private String srcIp;
    private String imei;
    private String srcUser;

    private long logTime;
    private List<String> output;

    public ParsedLog() {
    }

    public ParsedLog(String dateTime, String username, String srcIp, String imei, String srcUser) {
        this.dateTime = dateTime;
        this.username = username;
        this.srcIp = srcIp;
        this.imei = imei;
        this.srcUser = srcUser;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUsername() {
        return username;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public String getImei() {
        return imei;
    }

    public String getSrcUser() {
        return srcUser;
    }

    public long getLogTime() {
        return logTime;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }

    public void setLogTime(long logTime) {
        this.logTime = logTime;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}