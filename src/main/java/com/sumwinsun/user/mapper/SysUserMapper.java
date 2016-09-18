package com.sumwinsun.user.mapper;

import com.sumwinsun.user.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public interface SysUserMapper {

    String getHelloWords();

    void addSysUser(SysUser user);

    SysUser getUserById(@Param("id") String id);
}
