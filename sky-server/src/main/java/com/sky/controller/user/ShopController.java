package com.sky.controller.user;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("usershopcontroller")
@RequestMapping("/user/shop")
public class ShopController {

    public static final String key="SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    public Result getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(key);
       log.info("获取到的状态：{}",shopStatus==1?"营业中":"打烊中");
       return Result.success(shopStatus);
    }

}
