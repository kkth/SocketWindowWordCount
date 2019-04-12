package com.hk.kafka.entity;

/**
 * Created by kunhe on 3/27/19.
 */
/*
Three types of invoke log
        {"requestId":"9a550dd341e748ed8cc21006953260a1","serviceId":"dbfe3c5ddbbb4a91a4351d851e3b0494","httpStatus":200,"usedTime":11,"printTime":1553670293886}
        {"requestId":"9a550dd341e748ed8cc21006953260a1","serviceId":"quota","httpStatus":200,"usedTime":10,"printTime":1553670293865}
        {"requestId":"9a550dd341e748ed8cc21006953260a1","serviceId":"security","httpStatus":200,"usedTime":7,"printTime":1553670293855}
        */
public class InvokeLog {
    private String requestId;
    private String serviceId;
    private int httpStatus;
    private int usedTime;
    private long printTime;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(int usedTime) {
        this.usedTime = usedTime;
    }

    public long getPrintTime() {
        return printTime;
    }

    public void setPrintTime(long printTime) {
        this.printTime = printTime;
    }


    @Override
    public String toString() {
        return "InvokeLog{" +
                "requestId='" + requestId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", httpStatus=" + httpStatus +
                ", usedTime=" + usedTime +
                ", printTime=" + printTime +
                '}';
    }
}
