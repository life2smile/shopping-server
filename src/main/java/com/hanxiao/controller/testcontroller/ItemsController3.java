package com.hanxiao.controller.testcontroller;

import com.hanxiao.po.testpo.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenzhi on 17/3/11.
 */
@Controller
public class ItemsController3 {

    //方法和uri的映射
    @RequestMapping("/springmvcfirst/queryItems4.action")
    public ModelAndView queryItems() throws Exception {

        //调用service查找数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = new ArrayList<Items>();
        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("TinkPad T430联想笔记本!");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone 6苹果手机!");

        itemsList.add(items_1);
        itemsList.add(items_2);
        ModelAndView modelAndView = new ModelAndView();

        //相当于request的setArrtibute方法,jsp中可以通过itemsList获取数据
        modelAndView.addObject("itemsList", itemsList);
//        modelAndView.setViewName("/WEB-INF/jsp/items/index.jsp");

        modelAndView.setViewName("items/itemsList");

        return modelAndView;
    }

}