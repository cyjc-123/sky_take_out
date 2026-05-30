package com.sky.controller.user;


import com.sky.dto.OrdersDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.HistoryOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/order")
@Slf4j
public class historyOrdersController {

    @Autowired
    private HistoryOrdersService historyOrdersService;
    @GetMapping("/historyOrders")
    public Result list(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("查询历史记录订单：{}",ordersPageQueryDTO);
       PageResult list=historyOrdersService.list(ordersPageQueryDTO);
       return Result.success(list);

    }

}
