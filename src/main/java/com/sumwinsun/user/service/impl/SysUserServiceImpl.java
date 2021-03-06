package com.sumwinsun.user.service.impl;

import com.sumwinsun.common.spring.JedisSpring;
import com.sumwinsun.user.mapper.SysUserMapper;
import com.sumwinsun.user.pojo.SysUser;
import com.sumwinsun.user.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private JedisSpring jedisSpring;

    @Override
    public String getHelloWords() {
        String words = sysUserMapper.getHelloWords();
        words = jedisSpring.getJedisSource().get("token");
        return words;
    }

    @Override
    public void addSysUser(SysUser user) {

        sysUserMapper.addSysUser(user);
    }

    @Override
    public SysUser getUserById(String id) {
        SysUser user = sysUserMapper.getUserById(id);
        return user;
    }

    @Override
    public SysUser getUserByLoginName(String sysUserLoginName) {

        return sysUserMapper.getUserByLoginName(sysUserLoginName);
    }
}
