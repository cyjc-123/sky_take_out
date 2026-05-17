package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    void insertWithFlavor(DishDTO dishDTO);

    PageResult selectPage(DishPageQueryDTO dishPageQueryDTO);

    void deleteWithFlavor(List<Long> ids);

    DishVO selectInfo(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    void ifStatus(Integer status,Long id);
}
