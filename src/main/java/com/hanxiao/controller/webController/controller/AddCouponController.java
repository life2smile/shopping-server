package com.hanxiao.controller.webController.controller;

import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.webController.params.CouponRequestParams;
import com.hanxiao.mapper.CouponItemMapper;
import com.hanxiao.po.CouponItemData;
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
public class AddCouponController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "addCouponController", method = {RequestMethod.POST})
    @ResponseBody
    public WebResponseData addCouponCommodity(@RequestBody String request) {
        WebResponseData data = new WebResponseData();
        String code = "1001";
        String message = "success!";
        try {
            CouponRequestParams params = JSON.parseObject(request, CouponRequestParams.class);
            System.out.println(params+"-------------");
            insertCouponData(params);
        } catch (Exception e) {
            code = "1002";
            message = "fail!";
            e.printStackTrace();
        }
        data.setMessage(message);
        data.setCode(code);
        return data;
    }

    private void insertCouponData(CouponRequestParams params) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
        itemMapper.insertCouponData(getData(params));
        sqlSession.commit();
        sqlSession.close();
    }

    private CouponItemData getData(CouponRequestParams params) {
        String type = params.getType();
        String description = params.getDescription();//推广文案
        double originPrice = params.getOriginPrice();
        double couponPrice = params.getCouponPrice();
        double couponValue = originPrice - couponPrice;
        String imgUrl = params.getImgUrl();
        String actionUrl = params.getActionUrl();//跳转链
        boolean hasTicket = params.isHasTicket();

        String platFormImg = params.getPlatFormImg();
        String platformDesc = params.getPlatformDesc();

        CouponItemData data = new CouponItemData();
        data.setDescription(description);
        data.setCouponPrice(couponPrice);
        data.setCouponValue(couponValue);
        data.setOriginPrice(originPrice);
        data.setType(type);
        data.setImageUrl(imgUrl);
        data.setPlatformImg(platFormImg);
        data.setActionUrl(actionUrl);
        data.setPlatformDesc(platformDesc);
        data.setHasTicket(hasTicket);
        return data;
    }

}
