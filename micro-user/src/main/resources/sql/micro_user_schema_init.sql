CREATE SCHEMA IF NOT EXISTS `micro_user` DEFAULT CHARACTER SET utf8mb4;
USE `micro_user`;


CREATE TABLE `tourist` (
   `id`           bigint unsigned NOT NULL AUTO_INCREMENT,
   `app_key`      varchar(50)  NOT NULL DEFAULT '' COMMENT '应用key',
   `user_id`      varchar(50)  NOT NULL COMMENT '用户id',
   `tourist_id`   varchar(64)  NOT NULL COMMENT '游客id',
   `nickname`     varchar(128) NOT NULL DEFAULT '' COMMENT '昵称',
   `origin_app`   varchar(64)  NOT NULL DEFAULT '' COMMENT '来源app',
   `tenant_id`    varchar(50)  NOT NULL DEFAULT '' COMMENT '租户id',
   `created_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY `uk_uid` (`user_id`),
   UNIQUE KEY `uk_ak_tid` (`app_key`,`tourist_id`),
   KEY `idx_ak_uid` (`app_key`,`user_id`)
) ENGINE=InnoDB COMMENT='游客表';


CREATE TABLE `user` (
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT,
    `app_key`       varchar(50)  NOT NULL DEFAULT '' COMMENT '应用key',
    `user_id`    varchar(50)  NOT NULL COMMENT '用户id',
    `username`      varchar(128) NOT NULL DEFAULT '' COMMENT '用户名',
    `password`      varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`      varchar(128) NOT NULL DEFAULT '' COMMENT '用户昵称',
    `sex`           tinyint      NOT NULL DEFAULT '-1' COMMENT '性别（0-MALE/1-FEMALE/-1-未知）',
    `mobile`        varchar(64)  NOT NULL DEFAULT '' COMMENT '手机号',
    `email`         varchar(128) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `head_img_path` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像路径',
    `head_img_code` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像编号',
    `origin_app`    varchar(64)  NOT NULL DEFAULT '' COMMENT '来源app',
    `setup_mode`    varchar(128) NOT NULL DEFAULT '' COMMENT '创建方式（api:type）',
    `created_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid` (`user_id`),
    KEY `idx_ak_uid` (`app_key`,`user_id`)
) ENGINE=InnoDB COMMENT='用户表';


CREATE TABLE `user_ext` (
    `id`                  bigint unsigned NOT NULL AUTO_INCREMENT,
    `app_key`             varchar(50)  NOT NULL DEFAULT '' COMMENT '应用key',
    `user_id`          varchar(50)  NOT NULL COMMENT '用户id',
    `slogan`              varchar(255) NOT NULL DEFAULT '' COMMENT '个性签名',
    `novice_guide_status` tinyint      NOT NULL DEFAULT '0' COMMENT '新手引导状态（0-未完成/1-已完成）',
    `tags`                json         DEFAULT NULL COMMENT '个性标签',
    `ext`                 json         DEFAULT NULL COMMENT '其他扩展信息',
    `created_time`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid` (`user_id`),
    KEY `idx_ak_uid` (`app_key`,`user_id`)
) ENGINE=InnoDB COMMENT='用户扩展表';


CREATE TABLE `user_identity` (
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT,
    `app_key`        varchar(50)  NOT NULL DEFAULT '' COMMENT '应用key',
    `user_id`     varchar(50)  NOT NULL COMMENT '用户id',
    `grant_provider` varchar(32)  NOT NULL COMMENT '授权提供商（WECHAT/GOOGLE/APPLE）',
    `grant_id`       varchar(255) NOT NULL COMMENT '授权身份id',
    `mobile`         varchar(64)  NOT NULL DEFAULT '' COMMENT '手机号',
    `email`          varchar(128) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `grant_info`     json                  DEFAULT NULL COMMENT '授权信息',
    `user_info`      json                  DEFAULT NULL COMMENT '授权用户信息',
    `created_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid` (`user_id`),
    KEY `idx_ak_uid` (`app_key`,`user_id`)
) ENGINE=InnoDB COMMENT='用户身份表';


CREATE TABLE `user_login` (
    `id`              bigint unsigned NOT NULL AUTO_INCREMENT,
    `app_key`         varchar(50)     NOT NULL DEFAULT '' COMMENT '应用key',
    `user_id`      varchar(50)     NOT NULL COMMENT '用户id',
    `last_login_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登录时间',
    `login_count`     int unsigned    NOT NULL DEFAULT 1 COMMENT '登录次数',
    `created_time`    datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time`    datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid` (`user_id`),
    KEY `idx_ak_uid` (`app_key`,`user_id`)
) ENGINE=InnoDB COMMENT='用户登录表';
