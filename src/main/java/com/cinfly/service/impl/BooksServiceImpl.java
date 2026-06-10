package com.cinfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cinfly.constant.PageResult;
import com.cinfly.dto.bookPageQueryDto;
import com.cinfly.entity.Books;
import com.cinfly.mapper.BooksMapper;
import com.cinfly.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书籍表 服务实现类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-09
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements IBooksService {
    @Autowired
    private BooksMapper booksMapper;
    @Override
    public PageResult pageQuery(bookPageQueryDto bookPageQueryDto) {
      Page<Books>page=new Page<>(bookPageQueryDto.getPage(), bookPageQueryDto.getSize());
        LambdaQueryWrapper<Books> wrapper = new LambdaQueryWrapper<Books>()
                .like(bookPageQueryDto.getName() != null,Books::getTitle, bookPageQueryDto.getName())
                .eq(   bookPageQueryDto.getAuthor()!=null,Books::getAuthor, bookPageQueryDto.getAuthor())
                .eq(bookPageQueryDto.getCategoryId()!=null,Books::getCategoryId, bookPageQueryDto.getCategoryId())
                .eq(bookPageQueryDto.getStatus()!=null,Books::getStatus, bookPageQueryDto.getStatus());
       Page<Books> pageResult=booksMapper.pageQuery(page,wrapper);
       return new PageResult(pageResult.getTotal(),pageResult.getRecords());
    }
}
