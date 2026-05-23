package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j

public class DishController {
   @Autowired
    private DishService dishService;
    @PostMapping
    public Result insert(@RequestBody DishDTO dishDTO){
         log.info("新增菜品：{}",dishDTO);
         dishService.insertWithFlavor(dishDTO);
        return Result.success();
    }
    @GetMapping("/page")
    public Result selectPage(DishPageQueryDTO dishPageQueryDTO){
          log.info("分页查询：{}",dishPageQueryDTO);
          PageResult pageResult=dishService.selectPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品：{}",ids);
        dishService.deleteWithFlavor(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectInfo(@PathVariable Long id){
        log.info("查询回显：{}",id);
        DishVO dishVO=dishService.selectInfo(id);
        return Result.success(dishVO);
    }
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
             log.info("修改菜品：{}",dishDTO);
             dishService.updateWithFlavor(dishDTO);
             return Result.success();
    }
     @PostMapping("/status/{status}")
    public Result status( @PathVariable Integer status,@RequestParam Long id){
         log.info("状态：{},{}",status,id);
         dishService.ifStatus(status,id);
         return Result.success();
    }
    @GetMapping("/list")
    public Result listByCategoryId(@RequestParam Long categoryId){
        log.info("根据分类查询菜品：{}",categoryId);
        List<Dish> list=dishService.listByCategoryId(categoryId);
        return Result.success(list);
    }
}
