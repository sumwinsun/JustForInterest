package com.sumwinsun.activity.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.sumwinsun.activity.dto.ActivityExposer;
import com.sumwinsun.activity.dto.SeckillExecution;
import com.sumwinsun.activity.enums.SeckillStateEnum;
import com.sumwinsun.activity.exception.ActivityException;
import com.sumwinsun.activity.mapper.ActivityMapper;
import com.sumwinsun.activity.pojo.ActivityDetail;
import com.sumwinsun.activity.pojo.ActivityKilled;
import com.sumwinsun.activity.pojo.ActivityPrize;
import com.sumwinsun.activity.service.ActivityService;
import com.sumwinsun.common.spring.JedisSpring;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 秒杀服务层实现类
 * Created by Administrator on 2016/9/18 0018.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private JedisSpring jedisSpring;
    //  用于存储序列化的活动细节
    private RuntimeSchema<ActivityDetail> activityDetailRuntimeSchema = RuntimeSchema.createFrom(ActivityDetail.class);
    //  MD5加密拼接salt
    private String salt = "sfd%$#=_pjbggq";

    @Override
    public List<ActivityDetail> queryActivityList() {
        List<ActivityDetail> list = activityMapper.queryActivityList();
        ActivityPrize activityPrize ;
        for (ActivityDetail activityDetail : list){
            activityPrize = activityMapper.queryActivityPrizeByAcId(activityDetail.getActivityId());
            if (null != activityPrize){
                activityDetail.setActivityPrize(activityPrize);
                activityPrize = null;
            }
        }
        return list;
    }

    @Override
    public ActivityDetail queryActivityDetail(String activityId) throws ActivityException{
        ActivityDetail activityDetail = activityMapper.queryActivityDetail(activityId);
        if (null != activityDetail) {
            ActivityPrize activityPrize = activityMapper.queryActivityPrizeByAcId(activityDetail.getActivityId());
            if (null != activityPrize) activityDetail.setActivityPrize(activityPrize);
            else throw new ActivityException("数据异常，未查询到指定活动的奖品");
        } else {
            throw new ActivityException("未查询到指定活动");
        }
        return activityDetail;
    }

    /**
     * 根据活动id，去查询活动相关信息（开始，结束）
     *
     * @param activityId
     * @return
     */
    @Override
    public ActivityExposer activityExposer(String activityId) throws ActivityException{
        Jedis jedis = null;
        String md5Key = null;
        try {
            jedis = jedisSpring.getJedisSource();
            ActivityDetail activityDetail ;
            //先从缓存中读取，缓存中不存在再去读取数据库
            byte[] bytes = jedis.get(activityId.getBytes());
            if (null != bytes) {
                activityDetail = activityDetailRuntimeSchema.newMessage();
                ProtobufIOUtil.mergeFrom(bytes, activityDetail, activityDetailRuntimeSchema);
            } else {
                activityDetail = queryActivityDetail(activityId);
                bytes = ProtobufIOUtil.toByteArray(activityDetail, activityDetailRuntimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //将ActivityDetail存入缓存，设置1小时后失效；
                jedis.setex(activityId.getBytes(), 60 * 60, bytes);
            }
            Date now = new Date();
            //判断秒杀是否开始
            if (now.before(activityDetail.getActivityStartTime()) || now.after(activityDetail.getActivityEndTime()))
                return new ActivityExposer(false, activityId, activityDetail.getActivityStartTime().getTime(), activityDetail.getActivityEndTime().getTime(), now.getTime());
            md5Key = getMD5Key(activityId);
        } catch (ActivityException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return new ActivityExposer(true, activityId, md5Key);
    }

    /**
     * 执行秒杀
     * @param activityId
     * @param md5Key
     * @param killPhone
     * @return
     */
    @Override
    public SeckillExecution activitySeckillExecution(String activityId, String md5Key, String killPhone) {
        if (StringUtils.isEmpty(md5Key) || !getMD5Key(activityId).equals(md5Key))
            return new SeckillExecution(SeckillStateEnum.DATA_REWRITE,activityId);
        Map<String,Object> map = new HashMap<>();
        map.put("activityId",activityId);
        map.put("killPhone",killPhone);
        map.put("now",new Date());
        map.put("result",null);//存储秒杀执行结果，-1重复秒杀;-2系统异常;1秒杀成功;0秒杀结束
        try {
            activityMapper.activitySeckillExecution(map);
            int result = MapUtils.getIntValue(map,"result",-2);//默认设为-2（系统异常）
            if(result == 1){
                ActivityKilled activityKilled = activityMapper.queryActivityKilled(activityId,killPhone);
                return new SeckillExecution(SeckillStateEnum.SUCCESS,activityId,activityKilled);
            } else {
                return new SeckillExecution(SeckillStateEnum.stateOf(result),activityId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SeckillExecution(SeckillStateEnum.INNER_ERROR, activityId);
        }
    }

    /**
     * @param activityId
     * @return
     */
    private String getMD5Key(String activityId) {
        return DigestUtils.md5DigestAsHex((activityId + salt).getBytes());
    }
}
