package com.sumwinsun.activity.web;

import com.sumwinsun.activity.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用于活动的处理
 * Created by Administrator on 2016/9/18 0018.
 */
@Controller
@RequestMapping("activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/list")
    public String queryActivityList(Model model){
        //todo
        model.addAttribute("","");
        return "view/activity/list";
    }

    @RequestMapping(value = "/{activityId}/detail")
    public String queryActivityDetail(Model model){
        //TODO
        model.addAttribute("","");
        return "view/activity/detail";
    }

//    @RequestMapping(value = "/{activityId}/exposer")
//    @ResponseBody
//    public RequestResult<>
}
