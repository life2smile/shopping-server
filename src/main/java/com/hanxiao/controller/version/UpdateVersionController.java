package com.hanxiao.controller.version;

import com.alibaba.fastjson.JSON;
import com.hanxiao.mapper.VersionMapper;
import com.hanxiao.po.VersionData;
import com.hanxiao.pojo.VersionRequest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wenzhi on 17/6/21.
 */

@Controller
@RequestMapping("/v1/update.action")
public class UpdateVersionController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public VersionData updateVersion(@RequestBody String request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        VersionData newVersion = new VersionData();
        try {
            VersionRequest versionRequest = JSON.parseObject(request, VersionRequest.class);
            //更新用户登录频率
            VersionMapper versionMapper = sqlSession.getMapper(VersionMapper.class);
            versionMapper.insertUser(versionRequest.getDeviceId());

            //检查是否有新版本
            String oldVersion = versionRequest.getVersion();
            versionMapper = sqlSession.getMapper(VersionMapper.class);
            newVersion = versionMapper.getVersion();
            if (checkNeedUpdate(sqlSession, oldVersion, newVersion.getVersion())) {
                newVersion.setNewVersion(true);
            }
//            //app tab标签底部文字。用于动态配置tab标签页
//
//            //first tab
//            BottomTabData firstTab = new BottomTabData();
//            firstTab.setTabName("特惠区");
//            firstTab.setTabSelectImg("");
//            firstTab.setTabUnselectImg("");
//
//            //second tab
//            BottomTabData secondTab = new BottomTabData();
//            secondTab.setTabName("发现区");
//            secondTab.setTabSelectImg("");
//            secondTab.setTabUnselectImg("");
//
//            //third tab
//            BottomTabData thirdTab = new BottomTabData();
//            thirdTab.setTabName("区");
//            thirdTab.setTabSelectImg("");
//            thirdTab.setTabUnselectImg("");

        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback(true);
            newVersion.setNewVersion(false);
        }

        sqlSession.commit();
        sqlSession.clearCache();
        sqlSession.close();
        return newVersion;
    }

    /**
     * 版本格式为：*.*.*,初始版本0.0.1
     *
     * @param oldVersion
     * @param newVersion
     * @return
     */
    private boolean checkNeedUpdate(SqlSession sqlSession, String oldVersion, String newVersion) {
        if (oldVersion == null || newVersion == null) {
            return false;
        }
        try {
            String[] oldVersions = oldVersion.split("\\.");
            String[] newVersions = newVersion.split("\\.");

            for (int i = 0; i < newVersions.length; i++) {
                long newVer = Long.valueOf(newVersions[i]);
                long oldVer = Long.valueOf(oldVersions[i]);

                if (newVer == oldVer) {
                    continue;
                }
                if (newVer > oldVer) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        sqlSession.clearCache();//这里为了保证外部修改可以及时反馈到系统上，所以清空缓存。
        return false;
    }

}