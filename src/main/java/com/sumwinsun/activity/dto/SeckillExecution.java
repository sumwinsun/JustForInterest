package com.sumwinsun.activity.dto;

import com.sumwinsun.activity.enums.SeckillStateEnum;
import com.sumwinsun.activity.pojo.ActivityKilled;

/**
 * 秒杀活动执行结果
 * Created by Administrator on 2016/9/19 0019.
 */
public class SeckillExecution {
    //标识秒杀执行结果
    private int state;
    private String activityId;
    private String stateInfo;
    //秒杀成功的记录
    private ActivityKilled activityKilled;

    public SeckillExecution() { }

    public SeckillExecution(SeckillStateEnum seckillStateEnum, String activityId) {
        this.state = seckillStateEnum.getState();
        this.activityId = activityId;
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    public SeckillExecution(SeckillStateEnum seckillStateEnum, String activityId, ActivityKilled activityKilled) {

        this.state = seckillStateEnum.getState();
        this.activityId = activityId;
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.activityKilled = activityKilled;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public ActivityKilled getActivityKilled() {
        return activityKilled;
    }

    public void setActivityKilled(ActivityKilled activityKilled) {
        this.activityKilled = activityKilled;
    }

}
