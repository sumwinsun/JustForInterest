package com.sumwinsun.activity.enums;

/**
 * 秒杀结果枚举
 * Created by Administrator on 2016/9/19 0019.
 */
public enum SeckillStateEnum {
    SUCCESS(0,"秒杀成功"),
    END(1,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统内部错误"),
    DATA_REWRITE(-3,"数据被篡改");

    private int state;
    private String stateInfo;

    SeckillStateEnum() { }

    SeckillStateEnum(int state, String stateInfo) {
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

    public static SeckillStateEnum stateOf(int state){
        for (SeckillStateEnum seckillStateEnum : values())
            if (seckillStateEnum.getState() == state)
                return seckillStateEnum;
        return null;
    }

    public static void main(String[] args) {
        System.out.println(SeckillStateEnum.stateOf(-1));
    }
}
