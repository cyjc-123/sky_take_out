package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryOrdersMapper {

    Page<Orders> selectPage(OrdersPageQueryDTO ordersPageQueryDTO);
}
