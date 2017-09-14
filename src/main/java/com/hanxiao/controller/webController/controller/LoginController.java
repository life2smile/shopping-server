package com.hanxiao.controller.webController.controller;


import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.webController.params.LoginParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenzhi on 17/9/14.
 */
@Controller
@RequestMapping("/v1")
public class LoginController {
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    @ResponseBody
    public WebResponseData loginProcess(@RequestBody String request, Model model) throws Exception {
        WebResponseData data = new WebResponseData();
        LoginParams params = JSON.parseObject(request, LoginParams.class);
        String username = params.getUsername();
        String password = params.getPassword();
        if ("welcome".equals(username) && "good".equals(password)) {
            data.setMessage("success!");
            data.setCode("1001");
            return data;
        }
        data.setMessage("用户名或者密码错误!");
        data.setCode("1004");
        return data;
    }
}
