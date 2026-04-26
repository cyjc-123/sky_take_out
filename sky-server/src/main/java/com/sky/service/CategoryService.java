package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;


public interface CategoryService {
    PageResult selectPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void JudgeStatus(Integer status, Long id);

    void update(CategoryDTO category);

    void insert(CategoryDTO category);

    List<Category> selectBytype(Integer type);

    void delete(Long id);
}
