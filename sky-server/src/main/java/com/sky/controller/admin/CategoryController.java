package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
   @Autowired
    private CategoryService categoryService;
   @GetMapping("/page")
   public Result selectPage(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult=categoryService.selectPage(categoryPageQueryDTO);
        return Result.success(pageResult);
   }
    @PostMapping("/status/{status}")
    public Result JudgeStatus(@PathVariable Integer status, Long id){
        log.info("员工状态：{},员工id:{}",status,id);
        categoryService.JudgeStatus(status,id);
        return Result.success();
    }
    @PostMapping
    public Result insert(@RequestBody CategoryDTO category){
       log.info("新增分类：{}",category);
       categoryService.insert(category);
       return Result.success();
    }
    @GetMapping("/list")
    public Result selectBytype(Integer type){
     log.info("根据分类查询：{}",type);
         List<Category> category=categoryService.selectBytype(type);
         return Result.success(category);
    }
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
       log.info("修改分类：{}",categoryDTO);
       categoryService.update(categoryDTO);
       return Result.success();
    }
    @DeleteMapping
    public Result delete(Long id){
       log.info("删除：{}",id);
       categoryService.delete(id);
       return Result.success();
    }
}
