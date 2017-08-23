package com.hanxiao.mapper;

import com.hanxiao.po.CouponItemTitle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface TitleMapper {
    List<CouponItemTitle> getTitles(@Param("type") String type) throws Exception;

    List<CouponItemTitle> getTitlesWithOffset(Map<String, Integer> map) throws Exception;

    void insertTitle(CouponItemTitle data) throws Exception;

    void updateTitle(CouponItemTitle data) throws Exception;

    void deleteTitle(long id) throws Exception;

}
