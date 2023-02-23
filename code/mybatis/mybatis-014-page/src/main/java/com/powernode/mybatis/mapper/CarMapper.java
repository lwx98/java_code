package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {

    /**
     * 查询所有的Car，通过分页查询插件PageHelper完成。
     * @return
     */
    List<Car> selectAll();

    /**
     * 分页查询
     * @param startIndex 起始下标。
     * @param pageSize 每页显示的记录条数
     * @return
     */
    List<Car> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
