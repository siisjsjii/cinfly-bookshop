package com.cinfly.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 书籍表
 * </p>
 *
 * @author cinfly
 * @since 2026-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("books")
@NoArgsConstructor
@AllArgsConstructor
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 书名
     */
    @TableField("title")
    private String title;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 1代表经典文学类,2代表科幻类,3代表玄幻类,4代表言情类
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 封面图URL（OSS）
     */
    @TableField("image")
    private String image;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 库存数量
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 销量
     */
    @TableField("sales_count")
    private Long salesCount;

    /**
     * 收藏次数
     */
    @TableField("favorite_count")
    private Long favoriteCount;

    /**
     * 状态：0下架 1上架 2缺货
     */
    @TableField("status")
    private Integer status;

    /**
     * 书籍简介
     */
    @TableField("description")
    private String description;

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


}
