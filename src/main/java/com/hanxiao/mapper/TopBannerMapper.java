package com.hanxiao.mapper;

import com.hanxiao.po.TopBannerData;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface TopBannerMapper {
    List<TopBannerData> getTopBanner(Map<String, Object> map) throws Exception;
}
