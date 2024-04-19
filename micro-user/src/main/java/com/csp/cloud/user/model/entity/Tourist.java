package com.csp.cloud.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游客表
 * </p>
 *
 * @author chensiping
 * @since 2024-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Tourist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 应用key
     */
    @TableField("app_key")
    private String appKey;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 游客id
     */
    @TableField("tourist_id")
    private String touristId;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 来源app
     */
    @TableField("origin_app")
    private String originApp;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private String tenantId;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

}
