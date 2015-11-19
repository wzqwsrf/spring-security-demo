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
    public ModelAndView admin() {
        return new ModelAndView("/admin");
    }


    /**
     * anonymously
     */
    @RequestMapping(value = "anonymously.jsp")
    public ModelAndView anonymously() {
        return new ModelAndView("/anonymously");
    }


    /**
     * success
     */
    @RequestMapping(value = "success.jsp")
    public ModelAndView success() {
        return new ModelAndView("/success");
    }


    /**
     * 403
     */
    @RequestMapping(value = "403.jsp")
    public ModelAndView deny() {
        return new ModelAndView("/403");
    }

    /**
     * 403
     */
    @RequestMapping(value = "error.jsp")
    public ModelAndView error() {
        return new ModelAndView("/error");
    }


}
