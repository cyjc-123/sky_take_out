package com.sky.mapper;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> select(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category category);
@Insert("insert into category (type, name, sort, create_time, update_time, create_user, update_user)" +
        " VALUES (#{type}, #{name}, #{sort}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);
@Select("select * from category where type=#{type}")
    List<Category> selectByType(Integer type);
@Delete("delete from category where id=#{id}")
    void deleteByid(Long id);
}
