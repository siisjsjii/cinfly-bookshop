package com.cinfly.controller;

import com.cinfly.constant.PageResult;
import com.cinfly.constant.Result;
import com.cinfly.dto.PageQueryDto;
import com.cinfly.entity.Books;
import com.cinfly.mapper.BooksMapper;
import com.cinfly.service.IBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.CallSite;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/book")
@Slf4j
@Tag(name = "书籍相关接口")
public class BookController {
    @Autowired
    private IBooksService booksService;
    @PostMapping
    @Operation(summary = "新增书籍")
    public Result insert(@RequestBody Books book)
    {
        log.info("插入书籍：{}", book);
        book.setSalesCount(0L);
        book.setFavoriteCount(0L);
        book.setStatus(1);
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());
        booksService.save(book);
        return Result.success();


    }
    @GetMapping("/list")
    @Operation(summary = "获取所有书籍")
    public Result<PageResult>pageQuery(PageQueryDto pageQueryDto)
    {
        log.info("获取所有书籍");
        PageResult pageResult=booksService.pageQuery( pageQueryDto);
        return Result.success(pageResult);

    }
    @PutMapping("/{id}")
    @Operation(summary = "修改书籍")
    public Result update(@PathVariable Long id, @RequestBody Books book)
    {
        log.info("修改书籍：{}", book);
        book.setId( id);
        book.setUpdateTime(LocalDateTime.now());
        booksService.updateById(book);
        return Result.success();
    }
}
