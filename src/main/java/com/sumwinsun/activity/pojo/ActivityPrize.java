package com.sumwinsun.activity.pojo;

import java.util.Date;

public class ActivityPrize {
  private String prizeId;
  private String prizeName;
  private Long prizeNum;
  private Date prizeAddTime;
  private String prizeDesc;

  public String getPrizeId() {
    return prizeId;
  }

  public void setPrizeId(String prizeId) {
    this.prizeId = prizeId;
  }

  public String getPrizeName() {
    return prizeName;
  }

  public void setPrizeName(String prizeName) {
    this.prizeName = prizeName;
  }

  public Long getPrizeNum() {
    return prizeNum;
  }

  public void setPrizeNum(Long prizeNum) {
    this.prizeNum = prizeNum;
  }

  public Date getPrizeAddTime() {
    return prizeAddTime;
  }

  public void setPrizeAddTime(Date prizeAddTime) {
    this.prizeAddTime = prizeAddTime;
  }

  public String getPrizeDesc() {
    return prizeDesc;
  }

  public void setPrizeDesc(String prizeDesc) {
    this.prizeDesc = prizeDesc;
  }

  @Override
  public String toString() {
    return "ActivityPrize{" +
            "prizeId='" + prizeId + '\'' +
            ", prizeName='" + prizeName + '\'' +
            ", prizeNum=" + prizeNum +
            ", prizeAddTime=" + prizeAddTime +
            ", prizeDesc='" + prizeDesc + '\'' +
            '}';
  }
}
