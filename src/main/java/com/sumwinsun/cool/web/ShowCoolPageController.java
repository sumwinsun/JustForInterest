package com.sumwinsun.cool.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
@Controller
@RequestMapping("cool")
public class ShowCoolPageController {

    @RequestMapping(value = "spark")
    public String toSpark(){
        return "cool/spark";
    }
}
