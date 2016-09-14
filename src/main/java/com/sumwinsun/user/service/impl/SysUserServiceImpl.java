package com.sumwinsun.user.service.impl;

import com.sumwinsun.user.mapper.SysUserMapper;
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

    @Override
    public String getHelloWords() {
        String words = sysUserMapper.getHelloWords();
        return words;
    }
}
