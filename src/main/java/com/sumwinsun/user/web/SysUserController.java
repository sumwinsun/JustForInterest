package com.sumwinsun.user.web;

import com.sumwinsun.user.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
@Controller
@RequestMapping(value = "sysUserController")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping(value = "hello")
    public String sayHello(){
        String words = sysUserService.getHelloWords();
        return words;
    }
}
