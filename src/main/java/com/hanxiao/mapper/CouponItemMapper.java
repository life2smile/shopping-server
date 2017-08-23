package com.hanxiao.mapper;

import com.hanxiao.po.CouponItemData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface CouponItemMapper {
    List<CouponItemData> findCouponDataByType(@Param("type") String type) throws Exception;

    List<CouponItemData> findCouponDataWithOffset(Map<String, Object> map) throws Exception;

    List<CouponItemData> searchCouponDataWithOffset(Map<String, Object> map) throws Exception;

    void insertCouponData(CouponItemData data) throws Exception;

    void updateCouponData(CouponItemData data) throws Exception;

    void delCouponData(long id) throws Exception;

}
