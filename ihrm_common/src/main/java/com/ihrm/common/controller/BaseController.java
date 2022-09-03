package com.ihrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 公共控制器类
* */
public class BaseController {
    protected HttpServletResponse response;
    protected HttpServletRequest request;

    protected String companyId;
    protected  String companyName;

    @ModelAttribute
    public void setResAndReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        /*
        * 企业ID暂定1
        * */
        this.companyId = "1";
        this.companyName = "传智播客教育";
    }
}
