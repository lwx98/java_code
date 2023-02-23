package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Log;

import java.util.List;

public interface LogMapper {

    /**
     * 根据日期查询不同的表。获取表中所有的日志。
     * @param date
     * @return
     */
    List<Log> selectAllByTable(String date);

}
