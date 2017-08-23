package com.hanxiao.controller.testcontroller;

import com.hanxiao.po.testpo.Items;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenzhi on 17/3/11.
 */
public class ItemsController1 implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
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
        modelAndView.setViewName("/jsp/items/index.jsp");

        return modelAndView;
    }
}
