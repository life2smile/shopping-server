package com.hanxiao.dao;

import com.hanxiao.po.testpo.User;

import java.util.List;

/**
 * Created by wenzhi on 17/3/16.
 */
public interface UserDao {
    public User findUserById(int id) throws Exception;

    public void insertUser(User user) throws Exception;

    public void deleteUserById(int id) throws Exception;

    public List<User> findUserByName(String name) throws Exception;

}
