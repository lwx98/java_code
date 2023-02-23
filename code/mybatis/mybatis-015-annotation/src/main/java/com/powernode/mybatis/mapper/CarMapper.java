package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.*;

public interface CarMapper {

    @Select("select * from t_car where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "carNum", column = "car_num"),
            @Result(property = "brand", column = "brand"),
            @Result(property = "guidePrice", column = "guide_price"),
            @Result(property = "produceTime", column = "produce_time"),
            @Result(property = "carType", column = "car_type")
    })
    Car selectById(Long id);

    @Update("update t_car set car_num=#{carNum},brand=#{brand},guide_price=#{guidePrice},produce_time=#{produceTime},car_type=#{carType} where id=#{id}")
    int update(Car car);

    @Insert("insert into t_car values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})")
    int insert(Car car);

    @Delete("delete from t_car where id = #{id}")
    int deleteById(Long id);
}
