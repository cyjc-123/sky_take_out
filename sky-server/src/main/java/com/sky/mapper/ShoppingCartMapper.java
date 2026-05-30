package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> select(ShoppingCart shoppingCart);


    void insert(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number=#{number} where id=#{id}")
    void updateNumber(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id=#{userId}")
    void deleteAllByuserId(Long userId);
  @Delete("delete from shopping_cart where id=#{id}")
    void deleteById(Long id);
}
