package com.hanxiao.mapper;

import com.hanxiao.po.PromotionItemBanner;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface PromotionItemBannerMapper {
    List<PromotionItemBanner> getBanners(Map<String, Object> map) throws Exception;

    List<PromotionItemBanner> searchPromotionDataWithOffset(Map<String, Object> map) throws Exception;

    void insertBanners(PromotionItemBanner banner) throws Exception;

    void updateBanners() throws Exception;

}
