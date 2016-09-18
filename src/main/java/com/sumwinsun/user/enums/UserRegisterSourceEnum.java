package com.sumwinsun.user.enums;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public enum UserRegisterSourceEnum {
    WEB("1","网页端"),
    PHONE("0","移动端");


    private String state;
    private String stateInfo;

    UserRegisterSourceEnum() {}

    UserRegisterSourceEnum(String state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    /**
     * 根据index查询出注册来源的枚举
     * @param index
     * @return
     */
    public static UserRegisterSourceEnum stateOf(String index){
        if (StringUtils.isEmpty(index)) return null;
        for (UserRegisterSourceEnum userRegisterSourceEnum :values()){
            if(userRegisterSourceEnum.getState().equalsIgnoreCase(index))
                return userRegisterSourceEnum;
        }
        return null;
    }

}
