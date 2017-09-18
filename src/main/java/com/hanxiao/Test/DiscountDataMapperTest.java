package com.hanxiao.Test;

import com.hanxiao.mapper.DiscountItemMapper;
import com.hanxiao.po.DiscountItemData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wenzhi on 17/3/16.
 */
public class DiscountDataMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        String resource = "myBatis/sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂, 传入配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    public void testFindWithOffset() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("begin", 5);
        params.put("offset", 10);
        params.put("type", "");
        List<DiscountItemData> list = itemMapper.findDiscountDataWithOffset(params);

        System.out.println(initItemColor(list));
    }

    private List<DiscountItemData> initItemColor(List<DiscountItemData> list) {
        List<DiscountItemData> newList = new ArrayList<DiscountItemData>();
        if (list != null) {
            for (DiscountItemData item : list) {
                item.setPlatformBg(getColor(item));
                newList.add(item);
            }
        }
        return newList;
    }

    private String getColor(DiscountItemData item) {
        String color = null;
        if (item != null) {
            if (CouponUtils.ONLY_JINGDONG.equals(item.getPlatformDesc())) {
                color = "#C91623";
            }

            if (CouponUtils.ONLY_TAOBAO.equals(item.getPlatformDesc())) {
                color = "#ED6D00";
            }
            if (CouponUtils.ONLY_TIANMAO.equals(item.getPlatformDesc())) {
                color = "#970102";
            }

            if (CouponUtils.ONLY_MOGUJIE.equals(item.getPlatformDesc())) {
                color = "#FF4081";
            }
        }
        System.out.println(color + "--------------------");
        return color;
    }

    @Test
    public void testFindCouponDataByType() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        List<DiscountItemData> list = itemMapper.findDiscountDataByType("1");
        System.out.println(list);
    }

    @Test
    public void testUpdateCouponData() throws Exception {
        for (int i = 80; i < 120; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
            DiscountItemData data = new DiscountItemData();
            data.setDescription("很多人都来买了，谁不买，快点！" + i);
            data.setType(i % 6 + "");
            data.setTitle("绝对大甩卖!" + i);
            data.setOriginPrice(i + 10);
            data.setCouponPrice(i + 1);
            data.setDiscount((i + 10) / (i + 1));
            data.setId(i);
            if (i % 2 == 0) {
                data.setImageUrl("https://gw.alicdn.com/bao/uploaded/i2/TB1.5jLOpXXXXaKapXXXXXXXXXX_!!0-item_pic.jpg_640x480Q50s50.jpg_.webp");
            } else {
                data.setImageUrl("http://img0.imgtn.bdimg.com/it/u=3395581086,3505521106&fm=21&gp=0.jpg");
            }
            itemMapper.updateDiscountData(data);
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        itemMapper.delDiscountData(7);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testInsertCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        itemMapper.insertDiscountData(getData());
        sqlSession.commit();
        sqlSession.close();
    }

    public DiscountItemData getData() {
        String title = "孕妇打底裤秋装孕妇裤秋季外穿长裤2017新款潮妈春秋托腹秋冬裤子【包邮】";
        String desc = "孕妇打底裤秋装孕妇裤秋季外穿长裤2017新款潮妈春秋托腹秋冬裤子【包邮】";
        String type = "";
        String imgUrl = "https://img.alicdn.com/imgextra/i2/2433136455/TB2k.DjabwTMeJjSszfXXXbtFXa_!!2433136455.jpg_430x430q90.jpg";
        String actionUrl = "http://e22a.com/h.IX0zw1";
        String platformDesc = CouponUtils.ONLY_TIANMAO;
        double originPrice = 85.00;
        double promotionPrice = 29.00;
        double discount = 0;

        DiscountItemData data = new DiscountItemData();
        data.setDescription(desc);
        data.setTitle(title);
        data.setType(type);
        data.setImageUrl(imgUrl);
        data.setActionUrl(actionUrl);
        data.setPlatformDesc(platformDesc);
        data.setOriginPrice(originPrice);
        data.setCouponPrice(promotionPrice);
        data.setDiscount(discount);
        return data;
    }

}
