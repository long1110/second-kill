package com.clw.controller;

import com.clw.core.util.R;
import com.clw.core.redis.RedisUtil;
import com.clw.entity.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
@Api("swaggerDemoController相关api")
public class demoController {

    @Autowired
    private RedisUtil redisUtil;


    @GetMapping("/hello")
    @ApiOperation(value = "demo",notes = "demo1")
    @ResponseBody
    public R hello(){
        return R.ok().put("page","Hello world");
    }
    @GetMapping("thymeleaf")
    public String tymeleaf(Model model){
        redisUtil.set("name","李四");
        model.addAttribute("name",redisUtil.get("name"));
        return "demo";
    }
}
