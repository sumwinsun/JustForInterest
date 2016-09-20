package com.sumwinsun.activity.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class ActivityDetail {
  private String activityId;
  private String activityName;
  private String activityPrizeId;
  private Date activityStartTime;
  private Date activityEndTime;
  private Date activityCreateTime;
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

  public Date getActivityStartTime() {
    return activityStartTime;
  }

  public void setActivityStartTime(Date activityStartTime) {
    this.activityStartTime = activityStartTime;
  }

  public Date getActivityEndTime() {
    return activityEndTime;
  }

  public void setActivityEndTime(Date activityEndTime) {
    this.activityEndTime = activityEndTime;
  }

  public Date getActivityCreateTime() {
    return activityCreateTime;
  }

  public void setActivityCreateTime(Date activityCreateTime) {
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
}
