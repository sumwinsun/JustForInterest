package com.sumwinsun.activity.service;

import com.sumwinsun.activity.dto.ActivityExposer;
import com.sumwinsun.activity.dto.SeckillExecution;
import com.sumwinsun.activity.pojo.ActivityDetail;

import java.util.List;

/**
 * 活动服务层接口
 * Created by Administrator on 2016/9/18 0018.
 */
public interface ActivityService {
    List<ActivityDetail> queryActivityList();

    ActivityDetail queryActivityDetail(String activityId);

    ActivityExposer activityExposer(String activityId);

    SeckillExecution activitySeckillExecution(String activityId, String md5Key, String killPhone);
}
