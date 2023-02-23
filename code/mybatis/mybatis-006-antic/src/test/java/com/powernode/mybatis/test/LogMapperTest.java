package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.LogMapper;
import com.powernode.mybatis.pojo.Log;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class LogMapperTest {
    @Test
    public void testSelectAllByTable(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LogMapper mapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logs = mapper.selectAllByTable("20220901");
        logs.forEach(log -> System.out.println(log));
    }
}