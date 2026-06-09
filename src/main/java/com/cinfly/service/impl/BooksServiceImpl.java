package com.cinfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cinfly.constant.PageResult;
import com.cinfly.dto.PageQueryDto;
import com.cinfly.entity.Books;
import com.cinfly.mapper.BooksMapper;
import com.cinfly.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageResult pageQuery(PageQueryDto pageQueryDto) {
      Page<Books>page=new Page<>(pageQueryDto.getPage(),pageQueryDto.getSize());
        LambdaQueryWrapper<Books> wrapper = new LambdaQueryWrapper<Books>()
                .like(pageQueryDto.getName() != null,Books::getTitle, pageQueryDto.getName())
                .eq(   pageQueryDto.getAuthor()!=null,Books::getAuthor, pageQueryDto.getAuthor())
                .eq(pageQueryDto.getCategoryId()!=null,Books::getCategoryId, pageQueryDto.getCategoryId());
       Page<Books> pageResult=booksMapper.pageQuery(page,wrapper);
       return new PageResult(pageResult.getTotal(),pageResult.getRecords());
    }
}
