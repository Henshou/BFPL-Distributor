package com.distributor.distributor.model;

public class TelemetryEvent {
    private String recordId;
    private String batchId;
    private String fileName;
    private String serviceName;
    private String metricName;
    private long metricValue;
    private long eventTimestamp;

    public TelemetryEvent() {}

    public TelemetryEvent(String recordId, String batchId, String fileName, String serviceName, String metricName, long metricValue, long eventTime) {
        this.recordId = recordId;
        this.batchId = batchId;
        this.fileName = fileName;
        this.serviceName = serviceName;
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.eventTimestamp = eventTime;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public long getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(long metricValue) {
        this.metricValue = metricValue;
    }

    public long getEventTime() {
        return eventTimestamp;
    }

    public void setEventTime(long eventTime) {
        this.eventTimestamp = eventTime;
    }
}
