package com.sky.controller.user;


import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
public class OrderController {
   @Autowired
    private OrderService orderService;
    @PostMapping("/submit")
    public Result<OrderSubmitVO> order(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单：{}",ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO=orderService.order(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }
    @GetMapping("/orderDetail/{id}")
    public Result selectDetail(@PathVariable Long id){
        log.info("查询订单详情：{}",id);
        OrderVO orderVO=orderService.selectDetailByorderID(id);
          return Result.success(orderVO);
    }
    @PutMapping("/cancel/{id}")
    public Result cancel(@PathVariable Long id){
        log.info("取消订单：{}",id);
       orderService.cancel(id);
        return Result.success();
    }
    @PostMapping("/repetition/{id}")
    public Result repetition(@PathVariable Long id){
        log.info("再来一单：{}",id);
        orderService.repetition(id);
        return Result.success();
    }
    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable Long id){
        log.info("催单：{}",id);
        orderService.reminder(id);
        return Result.success();
    }

}
