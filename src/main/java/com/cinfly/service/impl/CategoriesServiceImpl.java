package com.cinfly.service.impl;

import com.cinfly.entity.Categories;
import com.cinfly.mapper.CategoriesMapper;
import com.cinfly.service.ICategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-09
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements ICategoriesService {

}
