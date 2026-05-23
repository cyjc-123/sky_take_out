package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    void insertWithFlavor(DishDTO dishDTO);

    PageResult selectPage(DishPageQueryDTO dishPageQueryDTO);

    void deleteWithFlavor(List<Long> ids);

    DishVO selectInfo(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    void ifStatus(Integer status,Long id);
    /**
     * 根据分类id查询菜品选项
     * @param categoryId
     * @return
     */
    List<Dish> listByCategoryId(Long categoryId);
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */

}
