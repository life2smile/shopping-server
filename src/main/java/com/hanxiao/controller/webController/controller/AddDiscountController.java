package com.hanxiao.controller.webController.controller;

import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.webController.params.DiscountRequestParams;
import com.hanxiao.mapper.DiscountItemMapper;
import com.hanxiao.po.DiscountItemData;
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
@RequestMapping("/v1")
public class AddDiscountController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "addDiscountController", method = {RequestMethod.POST})
    @ResponseBody
    public WebResponseData addDiscountCommodity(@RequestBody String request) {
        WebResponseData data = new WebResponseData();
        String code = "1001";
        String message = "success!";
        try {
            DiscountRequestParams params = JSON.parseObject(request, DiscountRequestParams.class);
            insertDiscountData(params);
        } catch (Exception e) {
            code = "1002";
            message = "fail!";
            e.printStackTrace();
        }
        data.setMessage(message);
        data.setCode(code);
        return data;
    }

    private void insertDiscountData(DiscountRequestParams params) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        itemMapper.insertDiscountData(getData(params));
        sqlSession.commit();
        sqlSession.close();
    }

    private DiscountItemData getData(DiscountRequestParams params) {
        DiscountItemData data = new DiscountItemData();
        data.setDescription(params.getDesc());
        data.setTitle(params.getTitle());
        data.setType(params.getType());
        data.setImageUrl(params.getImgUrl());
        data.setActionUrl(params.getActionUrl());
        data.setPlatformDesc(params.getPlatformDesc());
        data.setOriginPrice(params.getOriginPrice());
        data.setCouponPrice(params.getPromotionPrice());
        data.setDiscount(params.getDiscount());
        return data;
    }

}
