package com.powernode.godbatis.test;

import com.powernode.godbatis.pojo.User;
import org.god.ibatis.core.SqlSession;
import org.god.ibatis.core.SqlSessionFactory;
import org.god.ibatis.core.SqlSessionFactoryBuilder;
import org.god.ibatis.utils.Resources;
import org.junit.Test;

public class UserMapperTest {
    @Test
    public void testInsertUser(){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行SQL
        User user = new User("3333", "孙悟空", "5000");
        int count = sqlSession.insert("user.insertUser", user);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById(){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object obj = sqlSession.selectOne("user.selectById", "3333");
        System.out.println(obj);
        sqlSession.close();
    }
}
