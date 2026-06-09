package com.cinfly.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cinfly.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 书籍表 Mapper 接口
 * </p>
 *
 * @author cinfly
 * @since 2026-06-09
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {


    Page<Books> pageQuery(Page<Books> page, @Param("ew") LambdaQueryWrapper<Books> wrapper);
}
