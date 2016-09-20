package com.sumwinsun.activity.dto;

/**
 * 活动地址暴露
 * Created by Administrator on 2016/9/19 0019.
 */
public class ActivityExposer {
    private boolean flag;
    private String activityId;
    private String md5Key;
    private long start;
    private long end;
    private long now;

    public ActivityExposer() { }

    public ActivityExposer(boolean flag, String activityId, long start, long end, long now) {
        this.flag = flag;
        this.activityId = activityId;
        this.start = start;
        this.end = end;
        this.now = now;
    }

    public ActivityExposer(boolean flag, String activityId, String md5Key) {
        this.flag = flag;
        this.activityId = activityId;
        this.md5Key = md5Key;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "ActivityExposer{" +
                "flag=" + flag +
                ", activityId='" + activityId + '\'' +
                ", md5Key='" + md5Key + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", now=" + now +
                '}';
    }
}
