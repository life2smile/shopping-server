package com.hanxiao.mapper;

import com.hanxiao.po.VersionData;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface VersionMapper {
    VersionData getVersion() throws Exception;

    void insertVersion(VersionData data) throws Exception;

    void updateVersion(VersionData data) throws Exception;

    void deleteVersion(long id) throws Exception;

    //这里记录下用户登陆的设备，由于就这一个方法所以暂且放在这里吧，有精力可以单拉
    void insertUser(String deviceId) throws Exception;

}
