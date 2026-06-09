package com.cinfly.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cinfly.constant.Result;
import com.cinfly.dto.UpdateCategoryNameDto;
import com.cinfly.entity.Books;
import com.cinfly.entity.Categories;
import com.cinfly.exception.BusinessException;
import com.cinfly.service.IBooksService;
import com.cinfly.service.ICategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
@Tag(name = "书籍分类相关接口")
public class CategoryController {
    @Autowired
    private ICategoriesService categoriesService;
    @Autowired
    private IBooksService booksService;

    @Operation(summary = "获取所有书籍分类")
    @GetMapping("/list")
    public Result<List<Categories>>list()
    {
        List<Categories> list = categoriesService.list();
        return Result.success(list);
    }
    @PostMapping
    @Operation(summary = "新增书籍分类")
    public Result insert(@RequestBody UpdateCategoryNameDto updateCategoryNameDto)
    {
        String name=updateCategoryNameDto.getName();
        log.info("新增书籍分类：{}", name);
        Categories categories = new Categories();
        categories.setName(name);
        categories.setCreateTime(LocalDateTime.now());
        categories.setUpdateTime(LocalDateTime.now());
        categoriesService.save(categories);
        return Result.success();
    }
    @PutMapping("/{id}")
    @Operation(summary = "修改书籍分类")
    public Result update(@PathVariable Long id, @RequestBody UpdateCategoryNameDto updateCategoryNameDto)
    {
        log.info("修改书籍分类：{}", updateCategoryNameDto);
        Categories categories = new Categories();
        categories.setId(id);
        categories.setName(updateCategoryNameDto.getName());
        categories.setUpdateTime(LocalDateTime.now());
        categoriesService.updateById(categories);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "删除书籍分类")
    public Result delete(@PathVariable Long id)
    {
        log.info("删除书籍分类：{}", id);
        //查询分类下是否有书籍,如果有那么删除失败
        List<Books> list = booksService.lambdaQuery().eq(Books::getCategoryId, id).list();
        if(list!=null&&list.size()>0)
        {
            throw new BusinessException("该分类下有书籍,不能删除");
        }
        categoriesService.removeById(id);
        return Result.success();
    }

}
