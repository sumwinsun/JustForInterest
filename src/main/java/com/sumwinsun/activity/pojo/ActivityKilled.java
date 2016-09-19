package com.sumwinsun.activity.pojo;

import com.sumwinsun.user.pojo.SysUser;

import java.util.Date;

/**
 * 执行秒杀成功的记录
 */
public class ActivityKilled {
  private String killId;
  private String killUserId;
  private Long killState;
  private Date killCreateTime;
  private String killPrizeId;
  private String killActivityId;

  private SysUser user;
  private ActivityDetail activityDetail;
  private ActivityPrize activityPrize;

  public ActivityKilled() { }

  public String getKillId() {
    return killId;
  }

  public void setKillId(String killId) {
    this.killId = killId;
  }

  public String getKillUserId() {
    return killUserId;
  }

  public void setKillUserId(String killUserId) {
    this.killUserId = killUserId;
  }

  public Long getKillState() {
    return killState;
  }

  public void setKillState(Long killState) {
    this.killState = killState;
  }

  public Date getKillCreateTime() {
    return killCreateTime;
  }

  public void setKillCreateTime(Date killCreateTime) {
    this.killCreateTime = killCreateTime;
  }

  public String getKillPrizeId() {
    return killPrizeId;
  }

  public void setKillPrizeId(String killPrizeId) {
    this.killPrizeId = killPrizeId;
  }

  @Override
  public String toString() {
    return "ActivityKilled{" +
            "killId='" + killId + '\'' +
            ", killUserId='" + killUserId + '\'' +
            ", killState=" + killState +
            ", killCreateTime=" + killCreateTime +
            ", killPrizeId='" + killPrizeId + '\'' +
            ", killActivityId='" + killActivityId + '\'' +
            ", user=" + user +
            ", activityDetail=" + activityDetail +
            ", activityPrize=" + activityPrize +
            '}';
  }

  public String getKillActivityId() {
    return killActivityId;
  }

  public void setKillActivityId(String killActivityId) {
    this.killActivityId = killActivityId;
  }

  public SysUser getUser() {
    return user;
  }

  public void setUser(SysUser user) {
    this.user = user;
  }

  public ActivityDetail getActivityDetail() {
    return activityDetail;
  }

  public void setActivityDetail(ActivityDetail activityDetail) {
    this.activityDetail = activityDetail;
  }

  public ActivityPrize getActivityPrize() {
    return activityPrize;
  }

  public void setActivityPrize(ActivityPrize activityPrize) {
    this.activityPrize = activityPrize;
  }
}
