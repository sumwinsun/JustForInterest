package com.sumwinsun.activity.web;

import com.sumwinsun.activity.dto.ActivityExposer;
import com.sumwinsun.activity.dto.SeckillExecution;
import com.sumwinsun.activity.service.ActivityService;
import com.sumwinsun.common.dto.RequestResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String queryActivityDetail(@PathVariable("activityId")String activityId, Model model){
        //TODO
        model.addAttribute("","");
        return "view/activity/detail";
    }

    @RequestMapping(value = "/{activityId}/exposer",produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<ActivityExposer> activityExposer(@PathVariable("activityId")String activityId){
        //TODO
        return null;
    }

    /**
     *
     * @param activityId
     * @param md5Key
     * @param killPhone
     * @return
     */
    @RequestMapping(value = "/{activityId}/{md5Key}/execution",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public RequestResult<SeckillExecution> activitySeckillExecution(@PathVariable("activityId")String activityId, @PathVariable("md5Key")String md5Key
        , @CookieValue(value = "killPhone",required = false)String killPhone){
        //TODO
        return null;
    }
}
