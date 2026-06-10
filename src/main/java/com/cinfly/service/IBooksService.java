package com.cinfly.service;

import com.cinfly.constant.PageResult;
import com.cinfly.dto.bookPageQueryDto;
import com.cinfly.entity.Books;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 书籍表 服务类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-09
 */
public interface IBooksService extends IService<Books> {

    PageResult pageQuery(bookPageQueryDto bookPageQueryDto);
}
