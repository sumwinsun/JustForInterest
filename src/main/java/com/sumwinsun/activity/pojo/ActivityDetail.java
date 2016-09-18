package com.sumwinsun.activity.pojo;

import java.sql.Timestamp;

public class ActivityDetail {
  private String activityId;
  private String activityName;
  private String activityPrizeId;
  private java.sql.Timestamp activityStartTime;
  private java.sql.Timestamp activityEndTime;
  private java.sql.Timestamp activityCreateTime;
  private String activityDesc;

  private ActivityPrize activityPrize;

  public ActivityDetail() { }

  public String getActivityId() {
    return activityId;
  }

  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public String getActivityPrizeId() {
    return activityPrizeId;
  }

  public void setActivityPrizeId(String activityPrizeId) {
    this.activityPrizeId = activityPrizeId;
  }

  public Timestamp getActivityStartTime() {
    return activityStartTime;
  }

  public void setActivityStartTime(Timestamp activityStartTime) {
    this.activityStartTime = activityStartTime;
  }

  public Timestamp getActivityEndTime() {
    return activityEndTime;
  }

  public void setActivityEndTime(Timestamp activityEndTime) {
    this.activityEndTime = activityEndTime;
  }

  public Timestamp getActivityCreateTime() {
    return activityCreateTime;
  }

  public void setActivityCreateTime(Timestamp activityCreateTime) {
    this.activityCreateTime = activityCreateTime;
  }

  public String getActivityDesc() {
    return activityDesc;
  }

  public void setActivityDesc(String activityDesc) {
    this.activityDesc = activityDesc;
  }

  public ActivityPrize getActivityPrize() {
    return activityPrize;
  }

  public void setActivityPrize(ActivityPrize activityPrize) {
    this.activityPrize = activityPrize;
  }

  @Override
  public String toString() {
    return "ActivityDetail{" +
            "activityId='" + activityId + '\'' +
            ", activityName='" + activityName + '\'' +
            ", activityPrizeId='" + activityPrizeId + '\'' +
            ", activityStartTime=" + activityStartTime +
            ", activityEndTime=" + activityEndTime +
            ", activityCreateTime=" + activityCreateTime +
            ", activityDesc='" + activityDesc + '\'' +
            ", activityPrize=" + activityPrize +
            '}';
  }
}
