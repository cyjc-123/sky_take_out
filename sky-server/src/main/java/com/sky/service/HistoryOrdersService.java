package com.sky.service;


import com.sky.dto.OrdersPageQueryDTO;
import com.sky.result.PageResult;

public interface HistoryOrdersService {
    PageResult list(OrdersPageQueryDTO ordersPageQueryDTO);
}
