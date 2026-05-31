package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {

    BigDecimal countAmount(Map map);

    List<Orders> selectByTime(LocalDateTime beginTime, LocalDateTime endTime);
}
