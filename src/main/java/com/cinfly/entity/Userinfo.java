package com.cinfly.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cinfly
 * @since 2026-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("userinfo")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名（登录用）
     */
    @TableField("username")
    private String username;

    /**
     * 密码（BCrypt加密）
     */
    @TableField("password")
    private String password;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 个人头像URL（OSS）
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 收货地址
     */
    @TableField("address")
    private String address;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 用户表中的id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户姓名
     */
    @TableField("name")
    private String name;

    @TableField("email")
    private String email;


}
