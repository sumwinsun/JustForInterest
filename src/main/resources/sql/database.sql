# 创建数据库，并创建权限用户
# CREATE DATABASE 'ssm' CHARACTER SET utf8;
CREATE DATABASE interdb CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'sum'@'%' IDENTIFIED BY 'sum';
GRANT ALL PRIVILEGES ON interdb.* TO 'sum'@'%';
FLUSH PRIVILEGES;


#基础表创建
# system_info 系统版本表
CREATE TABLE system_info(
  id VARCHAR(64) NOT NULL PRIMARY KEY,
  system_version VARCHAR(30) NOT NULL ,
  update_time DATETIME,
  words VARCHAR(120)
);
INSERT INTO `system_info`(id,system_version,update_time,words)VALUES('1','2016年9月14日13:33:29',NOW(),'hello world!');


# 创建表
USE interdb;
CREATE TABLE `sys_user` (
  `sys_user_id` VARCHAR(64) NOT NULL,
  `sys_user_login_name` VARCHAR(50) NOT NULL,
  `sys_user_login_password` VARCHAR(50) NOT NULL,
  `sys_user_status` VARCHAR(1) NOT NULL,
  `sys_user_is_delete` VARCHAR(1) NOT NULL,
  `sys_user_register_datetime` DATETIME NOT NULL,
  `sys_user_register_source` VARCHAR(20) DEFAULT NULL,
  `sys_user_type` VARCHAR(20) NOT NULL,
  `sys_user_sex` VARCHAR(1) NOT NULL,
  `sys_user_is_email_active` VARCHAR(1) NOT NULL,
  `sys_user_is_mobile_active` VARCHAR(1) NOT NULL,
  `sys_user_register_type` VARCHAR(20) NOT NULL,
  `sys_user_pay_passwrod` VARCHAR(50) DEFAULT NULL,
  `sys_user_icon` VARCHAR(100) DEFAULT NULL,
  `sys_user_real_name` VARCHAR(20) DEFAULT NULL,
  `sys_user_email` VARCHAR(50) DEFAULT NULL,
  `sys_user_mobile` VARCHAR(20) DEFAULT NULL,
  `sys_user_weibo_id` VARCHAR(36) DEFAULT NULL,
  `sys_user_qq_id` VARCHAR(36) DEFAULT NULL,
  PRIMARY KEY (`sys_user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

# 创建表数据
INSERT  INTO `sys_user`(`sys_user_id`,`sys_user_login_name`,`sys_user_login_password`,`sys_user_status`,`sys_user_is_delete`,`sys_user_register_datetime`,`sys_user_register_source`,`sys_user_type`,`sys_user_sex`,`sys_user_is_email_active`,`sys_user_is_mobile_active`,`sys_user_register_type`,`sys_user_pay_passwrod`,`sys_user_icon`,`sys_user_real_name`,`sys_user_email`,`sys_user_mobile`,`sys_user_weibo_id`,`sys_user_qq_id`) VALUES ('1','YouMeek1','e10adc3949ba59abbe56e057f20f883e','0','N','2016-02-24 00:12:23','0','0','0','Y','Y','0','e10adc3949ba59abbe56e057f20f883e','','张觉恩1','363379441@qq.com','13800000001','','');
INSERT  INTO `sys_user`(`sys_user_id`,`sys_user_login_name`,`sys_user_login_password`,`sys_user_status`,`sys_user_is_delete`,`sys_user_register_datetime`,`sys_user_register_source`,`sys_user_type`,`sys_user_sex`,`sys_user_is_email_active`,`sys_user_is_mobile_active`,`sys_user_register_type`,`sys_user_pay_passwrod`,`sys_user_icon`,`sys_user_real_name`,`sys_user_email`,`sys_user_mobile`,`sys_user_weibo_id`,`sys_user_qq_id`) VALUES ('2','YouMeek2','e10adc3949ba59abbe56e057f20f883e','0','N','2016-02-24 00:12:23','0','0','0','Y','Y','0','e10adc3949ba59abbe56e057f20f883e','','张觉恩2','363379442@qq.com','13800000002','','');
INSERT  INTO `sys_user`(`sys_user_id`,`sys_user_login_name`,`sys_user_login_password`,`sys_user_status`,`sys_user_is_delete`,`sys_user_register_datetime`,`sys_user_register_source`,`sys_user_type`,`sys_user_sex`,`sys_user_is_email_active`,`sys_user_is_mobile_active`,`sys_user_register_type`,`sys_user_pay_passwrod`,`sys_user_icon`,`sys_user_real_name`,`sys_user_email`,`sys_user_mobile`,`sys_user_weibo_id`,`sys_user_qq_id`) VALUES ('3','YouMeek3','e10adc3949ba59abbe56e057f20f883e','0','N','2016-02-24 00:12:23','0','0','0','Y','Y','0','e10adc3949ba59abbe56e057f20f883e','','张觉恩3','363379443@qq.com','13800000003','','');
INSERT  INTO `sys_user`(`sys_user_id`,`sys_user_login_name`,`sys_user_login_password`,`sys_user_status`,`sys_user_is_delete`,`sys_user_register_datetime`,`sys_user_register_source`,`sys_user_type`,`sys_user_sex`,`sys_user_is_email_active`,`sys_user_is_mobile_active`,`sys_user_register_type`,`sys_user_pay_passwrod`,`sys_user_icon`,`sys_user_real_name`,`sys_user_email`,`sys_user_mobile`,`sys_user_weibo_id`,`sys_user_qq_id`) VALUES ('4','YouMeek4','e10adc3949ba59abbe56e057f20f883e','0','N','2016-02-24 00:12:23','0','0','0','Y','Y','0','e10adc3949ba59abbe56e057f20f883e','','张觉恩4','363379444@qq.com','13800000004','','');
INSERT  INTO `sys_user`(`sys_user_id`,`sys_user_login_name`,`sys_user_login_password`,`sys_user_status`,`sys_user_is_delete`,`sys_user_register_datetime`,`sys_user_register_source`,`sys_user_type`,`sys_user_sex`,`sys_user_is_email_active`,`sys_user_is_mobile_active`,`sys_user_register_type`,`sys_user_pay_passwrod`,`sys_user_icon`,`sys_user_real_name`,`sys_user_email`,`sys_user_mobile`,`sys_user_weibo_id`,`sys_user_qq_id`) VALUES ('5','YouMeek5','e10adc3949ba59abbe56e057f20f883e','0','N','2016-02-24 00:12:23','0','0','0','Y','Y','0','e10adc3949ba59abbe56e057f20f883e','','张觉恩5','363379445@qq.com','13800000005','','');

# 活动模块 #
#   活动明细表
CREATE TABLE `activity_detail` (
`activity_id` varchar(64) NOT NULL,
`activity_name` varchar(128) NOT NULL,
`activity_prize_id` varchar(64) NULL,
`activity_start_time` datetime NOT NULL,
`activity_end_time` datetime NOT NULL,
`activity_create_time` datetime NOT NULL,
`activity_desc` text NULL,
PRIMARY KEY (`activity_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


#   活动奖品表
CREATE TABLE `activity_prize` (
`prize_id` varchar(64) NOT NULL,
`prize_name` varchar(64) NOT NULL,
`prize_num` int DEFAULT 0,
`prize_add_time` datetime NOT NULL,
`prize_desc` text NULL,
PRIMARY KEY (`prize_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


#  秒杀成功记录表
CREATE TABLE `activity_killed` (
`kill_id` varchar(64) NOT NULL,
`kill_user_id` varchar(64) NULL,
`kill_state` int NULL,
`kill_create_time` datetime NULL,
`kill_prize_id` varchar(64) NOT NULL,
`kill_activity_id` varchar(64) NOT NULL,
PRIMARY KEY (`kill_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;