package com.sky.service.impl;

import com.sky.dto.DataOverViewQueryDTO;
import com.sky.entity.Orders;
import com.sky.mapper.ReportMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Override
    public TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> list=new ArrayList<>();
        while (!begin.equals(end)){
            begin=begin.plusDays(1);
            list.add(begin);
        }
        String string = list.toString();
        String substring = string.substring(1, string.length() - 1);
//        String join = StringUtils.join(list, ",");

        List<BigDecimal> turnoverlist=new ArrayList();
        for (LocalDate localDate : list) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            /*List<Orders>orders= reportMapper.selectByTime(beginTime,endTime);
             BigDecimal turnover = BigDecimal.ZERO;
            if (orders != null && !orders.isEmpty()) {
                for (Orders order : orders) {
                    if (Orders.COMPLETED.equals(order.getStatus())) {
                        // 累加已完成订单的金额
                        if (order.getAmount() != null) {
                            turnover = turnover.add(order.getAmount());
                        }
                    }
                }
            }
            turnoverlist.add(turnover)
            */
            Map map=new HashMap<>();
            map.put("beginTime",beginTime);
            map.put("endTime",endTime);
            map.put("status",Orders.COMPLETED);
            BigDecimal turnover=reportMapper.countAmount(map);
            turnoverlist.add(turnover);
        }
        String  turnoverstring= StringUtils.join(turnoverlist, ",");

        return TurnoverReportVO.builder()
                .dateList(substring)
                .turnoverList(turnoverstring)
                .build();
       /* // 1. 只查一次数据库，获取整个时间段的所有已完成订单
        LocalDateTime overallBeginTime = LocalDateTime.of(list.get(0), LocalTime.MIN);
        LocalDateTime overallEndTime = LocalDateTime.of(list.get(list.size() - 1), LocalTime.MAX);

        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", overallBeginTime);
        params.put("endTime", overallEndTime);
        params.put("status", Orders.COMPLETED);

        List<Orders> allOrders = reportMapper.selectByTimeAndStatus(params);

        // 2. 按日期分组统计营业额
        Map<LocalDate, BigDecimal> turnoverMap = new HashMap<>();
        if (allOrders != null && !allOrders.isEmpty()) {
            for (Orders order : allOrders) {
                LocalDate orderDate = order.getOrderTime().toLocalDate();
                BigDecimal amount = order.getAmount() != null ? order.getAmount() : BigDecimal.ZERO;

                turnoverMap.merge(orderDate, amount, BigDecimal::add);
            }
        }

        // 3. 按照日期列表顺序提取营业额
        List<BigDecimal> turnoverlist = new ArrayList<>();
        for (LocalDate localDate : list) {
            BigDecimal turnover = turnoverMap.getOrDefault(localDate, BigDecimal.ZERO);
            turnoverlist.add(turnover);
        }

        String turnoverstring = StringUtils.join(turnoverlist, ",");*/
    }

}
