package com.wzq.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wzqwsrf on 15/4/7.
 */
@Controller
@RequestMapping(value = "/")
public class ViewController {

    /**
     * login
     * 登陆界面
     */
    @RequestMapping(value = "login")
    public String login() {
        return "/login";
    }


    /**
     * index
     * 登陆界面
     */
    @RequestMapping(value = "index")
    public String index() {
        return "/index";
    }


    /**
     * admin
     */
    @RequestMapping(value = "admin")
    public String admin() {
        return "/admin";
    }


    /**
     * error
     */
    @RequestMapping(value = "error")
    public String error() {
        return "/error";
    }


    /**
     * 403
     */
    @RequestMapping(value = "403")
    public String deny() {
        return "/403";
    }

}
