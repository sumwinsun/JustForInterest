package com.sumwinsun.activity.enums;

/**
 * 活动状态枚举
 * Created by Administrator on 2016/9/19 0019.
 */
public enum ActivityStateEnum {
    NORMAL(0,"正常"),
    TIME_END(1,"超时结束"),
    COUNT_END(2,"无库存结束"),
    HIDE(-1,"暂不显示活动");

    private int state;
    private String stateInfo;

    ActivityStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static ActivityStateEnum stateOf(int state){
        for (ActivityStateEnum activityStateEnum : values())
            if (activityStateEnum.getState() == state)
                return activityStateEnum;
        return null;
    }
}
