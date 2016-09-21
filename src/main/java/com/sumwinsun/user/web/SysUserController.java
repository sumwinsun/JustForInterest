package com.sumwinsun.user.web;

import com.alibaba.druid.util.StringUtils;
import com.sumwinsun.common.annotation.Token;
import com.sumwinsun.common.config.SystemMsg;
import com.sumwinsun.common.dto.RequestResult;
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
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "form/login/index")
    @Token(save = true)
    public String registerUser(HttpServletRequest request){
//        System.out.println(request.getAttribute("token"));
//        System.out.println(request.getSession().getAttribute("token"));
//        System.out.println("添加Token："+request.getSession(false).getAttribute("token"));
//        System.out.println(request.getSession(true).getAttribute("token"));
        return "common/login";
    }

    @RequestMapping(value = "form/login")
    @ResponseBody
    @Token(remove = true)
    public RequestResult login(SysUser user, HttpServletRequest request){
//        System.out.println("是否删除Token：" + request.getSession(false).getAttribute("token"));
        if (null == user || StringUtils.isEmpty(user.getSysUserLoginName()))
            return new RequestResult(false,SystemMsg.NO_LOGINNAME_ERROR);
        SysUser _user = sysUserService.getUserByLoginName(user.getSysUserLoginName());
        if (null == _user){
            return new RequestResult(false, SystemMsg.NO_USER_ERROR);
        }
        if (!StringUtils.isEmpty(user.getSysUserLoginPassword()) && user.getSysUserLoginPassword().equals(_user.getSysUserLoginPassword())){
            request.getSession().setAttribute(SystemMsg.USER_KEY, _user);
            return new RequestResult(true,SystemMsg.SUCCESS);
        }
        return new RequestResult(false, SystemMsg.PASSWORD_ERROR);
    }


}
