package com.hk.kafka.elem;

import com.hk.kafka.entity.AuditLog;

/**
 * Created by kunhe on 3/27/19.
 */
public class CountByServiceId {
    private String serviceId;
    private long count;
    private AuditLog auditLog;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public AuditLog getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLog auditLog) {
        this.auditLog = auditLog;
    }

    @Override
    public String toString() {
        return "CountByServiceId{" +
                "serviceId='" + serviceId + '\'' +
                ", count=" + count +
                ", auditLog=" + auditLog +
                '}';
    }
}
