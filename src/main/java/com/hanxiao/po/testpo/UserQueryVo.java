package com.hanxiao.po.testpo;

import java.util.List;

/**
 * Created by wenzhi on 17/3/21.
 */
public class UserQueryVo {

    private List<Integer> ids;

    //保证查询条件
    private UserCustom userCustom;

    public UserCustom getUserCustom() {


        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;

    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
