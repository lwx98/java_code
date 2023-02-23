package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

public interface CarMapper {

    /**
     * 测试二级缓存
     * @param id
     * @return
     */
    Car selectById2(Long id);

    /**
     * 保存班级信息
     * @param cid
     * @param cname
     * @return
     */
    int insertClazz(@Param("cid") Integer cid, @Param("cname") String cname);

    /**
     * 根据id获取Car信息。
     * @param id
     * @return
     */
    Car selectById(Long id);

}
