package com.powernode.mybatis.mapper; // 包名也有叫做：com.powernode.mybatis.dao
//mapper包就是dao包。

import com.powernode.mybatis.pojo.Car;

import java.util.List;

// 一般使用mybatis的话，一般不叫做XXXDao了。一般都是XXXMapper。
public interface CarMapper { //CarDao。

    /**
     * 新增Car
     *
     * @param car
     * @return
     */
    int insert(Car car);

    /**
     * 根据id删除Car
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 修改汽车信息
     *
     * @param car
     * @return
     */
    int update(Car car);

    /**
     * 根据id查询汽车信息
     *
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 获取所有的汽车信息。
     *
     * @return
     */
    List<Car> selectAll();

}
