package com.wanwu.panta.web.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {
	//注解的使用
    @RequiresRoles(value={"ROLE_ADMIN","ROLE_CHECKER"}, logical=Logical.OR)
    @RequiresPermissions("refundQuery")
    @RequestMapping(value = "/refundQuery")
    @ResponseBody
    public String refundQuery(){
        return "refundQuery success!";
    }
    
    @RequiresRoles(value={"ROLE_ADMIN","ROLE_OPER"}, logical=Logical.OR)
    @RequiresPermissions("payOrder")
    @RequestMapping(value = "/payOrder")
    @ResponseBody
    public String payOrder(){
        return "payOrder success!";
    }
}
