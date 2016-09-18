package com.sumwinsun.user.enums;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public enum UserTypeEnum {
    CUSTOMER("0","普通用户"),
    INNER_MANAGER("1","内部管理员"),
    OUTER_MANAGER("2","外部管理员"),
    INNER("3","内部人员");

    private String state;
    private String stateInfo;

    UserTypeEnum() { }

    UserTypeEnum(String state, String stateInfo) {
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

    public static UserTypeEnum stateOf(String index){
        if (StringUtils.isEmpty(index)) return null;
        for (UserTypeEnum userTypeEnum : values()){
            if(userTypeEnum.getState().equalsIgnoreCase(index))
                return userTypeEnum;
        }
        return null;
    }
}
