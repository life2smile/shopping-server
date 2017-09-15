package com.hanxiao.controller.webController.controller;

import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.webController.params.PromotionCustomParams;
import com.hanxiao.controller.webController.params.PromotionGoodProductParams;
import com.hanxiao.mapper.CustomItemMapper;
import com.hanxiao.mapper.PromotionItemBannerMapper;
import com.hanxiao.mapper.PromotionItemMapper;
import com.hanxiao.po.CustomItemData;
import com.hanxiao.po.PromotionItemBanner;
import com.hanxiao.po.PromotionItemData;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wenzhi on 17/6/21.
 */

@Controller
@RequestMapping("/v1")
public class AddPromotionItemController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "addPromotionItemBannerController", method = {RequestMethod.POST})
    @ResponseBody
    public WebResponseData addPromotionCommodity(@RequestBody String request) {
        WebResponseData data = new WebResponseData();
        String code = "1001";
        String message = "success!";
        try {
            PromotionGoodProductParams params = JSON.parseObject(request, PromotionGoodProductParams.class);
            insertPromotionItemData(params);
        } catch (Exception e) {
            code = "1002";
            message = "fail!";
            e.printStackTrace();
        }
        data.setMessage(message);
        data.setCode(code);
        return data;
    }


    @RequestMapping(value = "getAllType", method = {RequestMethod.POST})
    @ResponseBody
    public WebResponseData getAllType(@RequestBody String request) {
        WebResponseData data = new WebResponseData();
        String code = "1001";
        String message = "success!";
        List<PromotionTypeData> list = new ArrayList<PromotionTypeData>();
        try {
            //查列表数据
            SqlSession sqlSession = sqlSessionFactory.openSession();
            PromotionItemMapper itemMapper = sqlSession.getMapper(PromotionItemMapper.class);
            HashMap<String, Object> params = new HashMap();
            params.put("begin", 0);
            params.put("offset", 10000);
            List<PromotionItemData> items = itemMapper.findPromotionDataWithOffset(params);
            for (PromotionItemData itemData : items) {
                PromotionTypeData typeData = new PromotionTypeData();
                typeData.setDesc(itemData.getDescription());
                typeData.setSubDesc(itemData.getSubDescription());
                typeData.setType(itemData.getType());
                list.add(typeData);
            }

        } catch (Exception e) {
            code = "1002";
            message = "fail!";
            e.printStackTrace();
        }
        data.setMessage(message);
        data.setCode(code);
        data.setData(list);
        return data;
    }


    @RequestMapping(value = "insertCustomItemController", method = {RequestMethod.POST})
    @ResponseBody
    public WebResponseData insertCustomItem(@RequestBody String request) {
        WebResponseData data = new WebResponseData();
        String code = "1001";
        String message = "success!";
        try {
            PromotionCustomParams params = JSON.parseObject(request, PromotionCustomParams.class);
            insertCumtomItem(params);
        } catch (Exception e) {
            code = "1002";
            message = "fail!";
            e.printStackTrace();
        }
        data.setMessage(message);
        data.setCode(code);
        return data;
    }

    public void insertCumtomItem(PromotionCustomParams params) throws Exception {
        CustomItemData data = new CustomItemData();

        data.setImgUrl(params.getImgUrl());
        data.setDescription(params.getDescription());
        data.setActionUrl(params.getActionUrl());
        data.setOriginPrice(params.getOriginPrice());
        data.setPromotionPrice(params.getPromotionPrice());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomItemMapper customItemMapper = sqlSession.getMapper(CustomItemMapper.class);
        customItemMapper.insertCustomItemData(data);
        sqlSession.commit();
        sqlSession.close();
    }

    public void insertPromotionItemData(PromotionGoodProductParams params) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PromotionItemBannerMapper itemMapper = sqlSession.getMapper(PromotionItemBannerMapper.class);
        itemMapper.insertBanners(getItemBannerData(params));
        sqlSession.commit();
        sqlSession.close();
    }

    //type:0:男装 1:女装2:吃货3:运动4:美妆5：男鞋子6：女鞋子
    private PromotionItemBanner getItemBannerData(PromotionGoodProductParams params) {
        PromotionItemBanner data = new PromotionItemBanner();
        data.setType(params.getType());
        data.setPrice(params.getPrice());
        data.setActionUrl(params.getActionUrl());
        data.setImgUrl(params.getImgUrl());
        data.setDescription(params.getDescription());
        return data;
    }

}
