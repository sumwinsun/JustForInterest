package com.sumwinsun.user.web;

import com.alibaba.druid.util.StringUtils;
import com.sumwinsun.common.spring.JedisSpring;
import com.sumwinsun.user.enums.UserRegisterSourceEnum;
import com.sumwinsun.user.pojo.SysUser;
import com.sumwinsun.user.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
@Controller
@RequestMapping(value = "user")
public class SysUserController {
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);
    @Resource
    private SysUserService sysUserService;
    @Resource
    private JedisSpring jedisSpring;

    @ResponseBody
    @RequestMapping(value = "hello")
    public String sayHello(){
        String words = null;
        Jedis jedis = null;
        try {
            words = sysUserService.getHelloWords();
            jedis = jedisSpring.getJedisSource();
            jedis.set("token","say I could");
            log.info(jedis.get("token"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return words;
    }

    @ResponseBody
    @RequestMapping(value = "add",produces = "application/json")
    public String addSysUser(SysUser sysUser){
        String result = "fail";
        SysUser user = new SysUser("sy","123456","normal","","N","Y",null,"15318271565");
        user.setSysUserId(UUID.randomUUID().toString());
        user.setSysUserIsDelete("N");
        user.setSysUserRegisterSource(UserRegisterSourceEnum.WEB.getState());
        user.setSysUserRegisterType("company");
        user.setSysUserType("manager");
        user.setSysUserRegisterDatetime(new Date());
        user.setSysUserSex("M");
        try {
            sysUserService.addSysUser(user);
            result = "true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "getUser",produces = "application/json")
    @ResponseBody
    public SysUser getUserById(String id){
        if(StringUtils.isEmpty(id)) return null;
        SysUser user = sysUserService.getUserById(id);
        return user;
    }

}
