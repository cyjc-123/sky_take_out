package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServerImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
       Long currentId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart=new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(currentId);
      List<ShoppingCart> list=shoppingCartMapper.select(shoppingCart);
      //判断当前菜品或套餐是否在购物车中
        if (list!=null&&list.size()>0){
            ShoppingCart cart = list.get(0);
            Integer number = cart.getNumber();
           cart.setNumber(number+1);
           shoppingCartMapper.updateNumber(cart);
        }else {
            //当前菜品或套餐不在购物车中
            Long dishId = shoppingCartDTO.getDishId();
            //如果是菜品，查询菜品数据
            if (dishId!=null){
                Dish dish = dishMapper.getById(dishId);
                String name = dish.getName();
                String image = dish.getImage();
                shoppingCart.setName(name);
                shoppingCart.setImage(image);
                shoppingCart.setAmount(dish.getPrice());
            }else {
                //当前是套餐
                Long setmealId = shoppingCartDTO.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);
        }

    }

    @Override
    public List<ShoppingCart> select() {
        Long currentId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUserId(currentId);
        List<ShoppingCart> list =shoppingCartMapper.select(shoppingCart);
        return list;
    }

    @Override
    public void deleteAll() {
        shoppingCartMapper.deleteAll();
    }

    @Override
    public void deleteOne(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart=new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        List<ShoppingCart> list = shoppingCartMapper.select(shoppingCart);
        ShoppingCart cart = list.get(0);
        //判断数量,数量>1
        if (cart.getNumber()>1){
            cart.setNumber(cart.getNumber()-1);
            shoppingCartMapper.updateNumber(cart);
        }else {
            //数量=1

            shoppingCartMapper.deleteById(cart.getId());
           /* Long dishId = cart.getDishId();
           if (dishId!=null){
               shoppingCartMapper.deleteById(cart.getId());
           }
           else {
               Long setmealId = cart.getSetmealId();
               shoppingCartMapper.deleteById(cart.getId());
           }*/
        }

    }
}
