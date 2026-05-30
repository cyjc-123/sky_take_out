package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.mapper.HistoryOrdersMapper;
import com.sky.mapper.OrderDetailMapper;
import com.sky.result.PageResult;
import com.sky.service.HistoryOrdersService;
import com.sky.utils.JwtUtil;
import com.sky.vo.DishVO;
import com.sky.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryOrdersServiceImpl implements HistoryOrdersService {
    @Autowired
    private HistoryOrdersMapper historyOrdersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public PageResult list(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(),ordersPageQueryDTO.getPageSize());
        Page<Orders> page=historyOrdersMapper.selectPage(ordersPageQueryDTO);
        List<OrderVO> list=new ArrayList<>();
        if (page!=null&&page.getTotal()>0){
            for (Orders orders : page) {
                Long id = orders.getId();
                List<OrderDetail> orderDetailList= orderDetailMapper.selectByOrderId(id);
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders,orderVO);
                orderVO.setOrderDetailList(orderDetailList);
                list.add(orderVO);
            }
        }
        return new PageResult(page.getTotal(),list);
    }
}
