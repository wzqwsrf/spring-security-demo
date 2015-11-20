package com.wzq.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "login.jsp")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }


    /**
     * index
     * 登陆界面
     */
    @RequestMapping(value = "index.jsp")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }


    /**
     * admin
     */
    @RequestMapping(value = "admin.jsp")
    public String admin() {
        return "/admin";
    }


    /**
     * error
     */
    @RequestMapping(value = "error.jsp")
    public ModelAndView anonymously() {
        return new ModelAndView("/error");
    }


    /**
     * 403
     */
    @RequestMapping(value = "403.jsp")
    public String deny() {
        return "/403";
    }

}
