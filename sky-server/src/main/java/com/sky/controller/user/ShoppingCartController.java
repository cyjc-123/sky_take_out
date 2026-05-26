package com.sky.controller.user;


import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/shoppingCart")
@RestController
@Slf4j
public class ShoppingCartController {
        @Autowired
        private ShoppingCartService shoppingCartService;
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车：{}",shoppingCartDTO);
        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<List<ShoppingCart>> select(){
          log.info("查询购物车：");
          List<ShoppingCart> list= shoppingCartService.select();
          return Result.success(list);
    }
    @DeleteMapping("/clean")
    public Result deleteAll(){
         log.info("清空购物车：");
         shoppingCartService.deleteAll();
         return Result.success();
    }
    @PostMapping("/sub")
    public Result deleteOne(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除购物车中一个：{}",shoppingCartDTO);
        shoppingCartService.deleteOne(shoppingCartDTO);
        return Result.success();
    }
}
