package com.sumwinsun.activity.mapper;

import com.sumwinsun.activity.pojo.ActivityDetail;
import com.sumwinsun.activity.pojo.ActivityKilled;
import com.sumwinsun.activity.pojo.ActivityPrize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 活动数据库交互接口
 * Created by Administrator on 2016/9/18 0018.
 */
@Repository
public interface ActivityMapper {
    List<ActivityDetail> queryActivityList();

    ActivityDetail queryActivityDetail(String activityId);

    ActivityPrize queryActivityPrizeByAcId(String activityId);

    void activitySeckillExecution(Map<String, Object> map);

    ActivityKilled queryActivityKilled(String activityId, String killPhone);
}
