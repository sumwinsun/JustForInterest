package com.sumwinsun.activity.service.impl;

import com.sumwinsun.activity.mapper.ActivityMapper;
import com.sumwinsun.activity.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

}
