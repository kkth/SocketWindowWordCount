package com.hk.kafka.entity;

/**
 * Created by kunhe on 12/12/18.
 */
/*
{"trackid":"81e1b4165705456d99642de891eb0c45",
"userid":"bingxin.li@tendcloud.com",
"appkey":"998bf8bd21c54571bdedef4d4d49cb87",
"serviceidnorth":"/data/user-idmapping/imeimd5/v2",
"serviceidsouth":"http://172.20.33.8:9237/mock/sync/test",
"serviceid":"3969208fdfa64528809c73107ca329cb",
"startts":1544613083166,
"endts":1544613083185,
"statusinner":200,
"statusouter":200,
"remoteIp":"172.26.126.27",
"inputCount":0,
"outputCount":0,
"validCount":1,
"matchCount":0,
"infoCount":0}
*/
public class AuditLog {
    private String            trackid;
    private String            userid;
    private String            appkey;
    private String            serviceidnorth;
    private String            serviceidsouth;
    private String            serviceid;
    private String            remoteIp;

    private long            startts;
    private long            endts;
    private long            statusinner;
    private long            statusouter;
    private long            inputCount;
    private long            outputCount;
    private long            validCount;
    private long            matchCount;
    private long            infoCount;

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getServiceidnorth() {
        return serviceidnorth;
    }

    public void setServiceidnorth(String serviceidnorth) {
        this.serviceidnorth = serviceidnorth;
    }

    public String getServiceidsouth() {
        return serviceidsouth;
    }

    public void setServiceidsouth(String serviceidsouth) {
        this.serviceidsouth = serviceidsouth;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public long getStartts() {
        return startts;
    }

    public void setStartts(long startts) {
        this.startts = startts;
    }

    public long getEndts() {
        return endts;
    }

    public void setEndts(long endts) {
        this.endts = endts;
    }

    public long getStatusinner() {
        return statusinner;
    }

    public void setStatusinner(long statusinner) {
        this.statusinner = statusinner;
    }

    public long getStatusouter() {
        return statusouter;
    }

    public void setStatusouter(long statusouter) {
        this.statusouter = statusouter;
    }

    public long getInputCount() {
        return inputCount;
    }

    public void setInputCount(long inputCount) {
        this.inputCount = inputCount;
    }

    public long getOutputCount() {
        return outputCount;
    }

    public void setOutputCount(long outputCount) {
        this.outputCount = outputCount;
    }

    public long getValidCount() {
        return validCount;
    }

    public void setValidCount(long validCount) {
        this.validCount = validCount;
    }

    public long getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(long matchCount) {
        this.matchCount = matchCount;
    }

    public long getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(long infoCount) {
        this.infoCount = infoCount;
    }


    @Override
    public String toString() {
        return "AuditLog{" +
                "trackid='" + trackid + '\'' +
                ", userid='" + userid + '\'' +
                ", appkey='" + appkey + '\'' +
                ", serviceidnorth='" + serviceidnorth + '\'' +
                ", serviceidsouth='" + serviceidsouth + '\'' +
                ", serviceid='" + serviceid + '\'' +
                ", remoteIp='" + remoteIp + '\'' +
                ", startts=" + startts +
                ", endts=" + endts +
                ", statusinner=" + statusinner +
                ", statusouter=" + statusouter +
                ", inputCount=" + inputCount +
                ", outputCount=" + outputCount +
                ", validCount=" + validCount +
                ", matchCount=" + matchCount +
                ", infoCount=" + infoCount +
                '}';
    }
}
