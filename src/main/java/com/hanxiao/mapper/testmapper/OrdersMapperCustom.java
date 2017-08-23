package com.hanxiao.mapper.testmapper;

import com.hanxiao.po.testpo.OrdersCustom;

import java.util.List;

/**
 * Created by wenzhi on 17/3/16.
 */
public interface OrdersMapperCustom {

    List<OrdersCustom> findOrdersUser() throws Exception;
}
