package com.hanxiao.pojo;

/**
 * Created by wenzhi on 17/6/23.
 */
public class VersionRequest {
    private String version;
    private String deviceId;

    public String getVersion() {
        return version;
    }

    public void setVersion(String currentVersion) {
        this.version = currentVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
