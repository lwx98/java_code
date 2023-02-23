package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface CarMapper {

    /**
     * 获取Car的总记录条数。
     * @return
     */
    Long selectTotal();

    /**
     * 查询所有的Car信息。但是启用了驼峰命名自动映射机制。
     * @return
     */
    List<Car> selectAllByMapUnderscoreToCamelCase();

    /**
     * 查询所有的Car信息。使用resultMap标签进行结果映射。
     * @return
     */
    List<Car> selectAllByResultMap();

    /**
     * 查询所有的Car，返回一个大Map集合。
     * Map集合的key是每条记录的主键值。
     * Map集合的value是每条记录。
     * {
     *      160={car_num=3333, id=160, guide_price=32.00, produce_time=2000-10-10, brand=奔驰E300L, car_type=新能源},
     *      161={car_num=4444, id=161, guide_price=32.00, produce_time=2000-10-10, brand=奔驰C200, car_type=新能源},
     *      162={car_num=9999, id=162, guide_price=30.00, produce_time=2020-10-11, brand=帕萨特, car_type=燃油车},
     *      163={car_num=9991, id=163, guide_price=30.00, produce_time=2020-11-11, brand=凯美瑞, car_type=燃油车},
     *      158={car_num=1111, id=158, guide_price=3.00, produce_time=2000-10-10, brand=比亚迪汉, car_type=新能源},
     *      159={car_num=2222, id=159, guide_price=32.00, produce_time=2000-10-10, brand=比亚迪秦, car_type=新能源}
     * }
     * @return
     */
    @MapKey("id") // 将查询结果的id值作为整个大Map集合的key。
    Map<Long, Map<String,Object>> selectAllRetMap();

    /**
     * 查询所有的Car信息。返回一个存放Map集合的List集合。
     * @return
     */
    List<Map<String,Object>> selectAllRetListMap();

    /**
     * 根据id获取汽车信息。将汽车信息放到Map集合中。
     * +-----+---------+----------+-------------+--------------+----------+
     * | id  | car_num | brand    | guide_price | produce_time | car_type |
     * +-----+---------+----------+-------------+--------------+----------+
     * | 158 | 1111    | 比亚迪汉 |        3.00 | 2000-10-10   | 新能源   |
     * +-----+---------+----------+-------------+--------------+----------+
     *
     * Map<String, Object>
     *     k                 v
     *     -----------------------
     *     "id"             158
     *     "car_num"        1111
     *     "brand"          比亚迪汉
     *     ....
     *
     * @param id
     * @return
     */
    Map<String, Object> selectByIdRetMap(Long id);


    /**
     * 根据id查询Car，id是主键。这个结果一定是一条。不可能有多条数据。那么这种情况可以使用List集合接收吗？
     * @param id
     * @return
     */
    List<Car> selectById2(Long id);

    /**
     * 根据品牌进行模糊查询。
     * 模糊查询的结果可能有多个，但是我采用一个POJO对象来接收会有问题吗？
     * @param brand
     * @return
     */
    Car selectByBrandLike(String brand);

    /**
     * 获取所有的Car
     * @return
     */
    List<Car> selectAll();

    /**
     * 根据id查询Car信息
     * @param id
     * @return
     */
    Car selectById(Long id);
}
