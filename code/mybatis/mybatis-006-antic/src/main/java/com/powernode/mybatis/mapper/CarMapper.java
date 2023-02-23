package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    /**
     * 插入Car信息，并且使用生成的主键值。
     * @param car
     * @return
     */
    int insertCarUseGeneratedKeys(Car car);

    /**
     * 根据汽车品牌进行模糊查询
     * @param brand
     * @return
     */
    List<Car> selectByBrandLike(String brand);

    /**
     * 批量删除，根据id
     * @param ids
     * @return
     */
    int deleteBatch(String ids);

    /**
     * 查询所有的汽车信息。然后通过asc升序，desc降序。
     * @param ascOrDesc
     * @return
     */
    List<Car> selectAllByAscOrDesc(String ascOrDesc);

    /**
     * 根据汽车类型获取汽车信息。
     * @param carType
     * @return
     */
    List<Car> selectByCarType(String carType);
}
