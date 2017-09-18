package com.hanxiao.Test;

/**
 * Created by wenzhi on 17/8/30.
 */
public class CouponUtils {

    public final static String SEARCH_MODULE_TICKET = "0";
    public final static String SEARCH_MODULE_FIND = "1";
    public final static String SEARCH_MODULE_BOUTIQUE = "2";

    public final static String TYPE_ALL = "0";
    public final static String TYPE_LINGSHI = "1";
    public final static String TYPE_NANZHUANG = "2";
    public final static String TYPE_NVZHUANG = "3";
    public final static String TYPE_BAIHUO = "4";
    public final static String TYPE_MUYING = "5";
    public final static String TYPE_JIAJU = "6";
    public final static String TYPE_SHUJI = "7";


    public final static String PNG_MOGUJIE = "mogujie_icon.png";
    public final static String PNG_JINGDONG = "jingdong_icon.png";
    public final static String PNG_TAOBAO = "taobao_icon.jpg";
    public final static String PNG_TIANMAO = "tianmao_icon.png";


    public final static String PLATFORM_MOGUJIE = "蘑菇街";
    public final static String PLATFORM_JINGDONG = "京东";
    public final static String PLATFORM_TIANMAO = "天猫";
    public final static String PLATFORM_TAOBAO = "淘宝";

    public final static String ONLY_MOGUJIE = "蘑菇街专享";
    public final static String ONLY_JINGDONG = "京东专享";
    public final static String ONLY_TIANMAO = "天猫专享";
    public final static String ONLY_TAOBAO = "淘宝专享";


    public static String getColor(String platform) {
        String color = null;
        if (CouponUtils.ONLY_JINGDONG.equals(platform)) {
            color = "#C91623";
        }
        if (CouponUtils.ONLY_MOGUJIE.equals(platform)) {
            color = "#FF4081";
        }
        if (CouponUtils.ONLY_TAOBAO.equals(platform)) {
            color = "#ED6D00";
        }
        if (CouponUtils.ONLY_TIANMAO.equals(platform)) {
            color = "#970102";
        }
        return color;
    }
}
