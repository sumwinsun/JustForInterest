package com.sumwinsun.activity.web;

import com.alibaba.druid.util.StringUtils;
import com.sumwinsun.activity.dto.ActivityExposer;
import com.sumwinsun.activity.dto.SeckillExecution;
import com.sumwinsun.activity.exception.ActivityException;
import com.sumwinsun.activity.pojo.ActivityDetail;
import com.sumwinsun.activity.service.ActivityService;
import com.sumwinsun.common.config.SystemMsg;
import com.sumwinsun.common.dto.RequestResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        RequestResult data = new RequestResult();
        try {
            List<ActivityDetail> list = activityService.queryActivityList();
            data.setFlag(true);
            data.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            data.setFlag(false);
            data.setMsg(SystemMsg.INNER_ERROR);
        }
        model.addAttribute("data", data);
        return "/activity/list";
    }

    @RequestMapping(value = "/{activityId}/detail")
    public String queryActivityDetail(@PathVariable("activityId")String activityId, Model model){
        RequestResult data = new RequestResult();
        try {
            ActivityDetail activityDetail = activityService.queryActivityDetail(activityId);
            data.setFlag(true);
            data.setData(activityDetail);
        } catch (ActivityException e){
            data.setFlag(false);
            data.setMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            data.setFlag(false);
            data.setMsg(SystemMsg.INNER_ERROR);
        }
        model.addAttribute("data",data);
        return "/activity/detail";
    }

    /**
     * 如果秒杀开始，暴露秒杀地址；否则记录秒杀进展信息
     * @param activityId    活动ID
     * @return  RequestResult<ActivityExposer>
     */
    @RequestMapping(value = "/{activityId}/exposer",produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<ActivityExposer> activityExposer(@PathVariable("activityId")String activityId){
        RequestResult<ActivityExposer> data = null;
        try {
            ActivityExposer activityExposer = activityService.activityExposer(activityId);
            data = new RequestResult<>(true,activityExposer);
        } catch (ActivityException e) {
            e.printStackTrace();
            data = new RequestResult<>(false,e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            data = new RequestResult<>(false, SystemMsg.INNER_ERROR);
        }
        return data;
    }

    /**
     * 执行秒杀操作
     * @param activityId 活动ID
     * @param md5Key    MD5验证
     * @param killPhone 秒杀号码
     * @return  RequestResult<SeckillExecution>
     */
    @RequestMapping(value = "/{activityId}/{md5Key}/execution",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public RequestResult<SeckillExecution> activitySeckillExecution(@PathVariable("activityId")String activityId, @PathVariable("md5Key")String md5Key
        , @CookieValue(value = "killPhone",required = false)String killPhone){
        if(StringUtils.isEmpty(killPhone))
            return new RequestResult<>(false,"未注册");
        SeckillExecution seckillExecution = activityService.activitySeckillExecution(activityId,md5Key,killPhone);
        return new RequestResult<>(true, seckillExecution);
    }

    @RequestMapping(value = "/time/now" , method = RequestMethod.GET , produces = {"application/json;charest=UTF-8"})
    @ResponseBody
    public RequestResult<Long> time(){
        Date now = new Date();

        return new RequestResult<>(true, now.getTime());
    }

    @RequestMapping(value = "/pickBag")
    public String pickBag(){
        return "/activity/pickBag/index";
    }
    @RequestMapping(value = "/flow")
    public String flow(){
        return "/activity/flow/index";
    }
}
