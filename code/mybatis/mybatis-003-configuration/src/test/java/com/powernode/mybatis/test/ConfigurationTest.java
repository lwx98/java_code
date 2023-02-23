package com.powernode.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void testDataSource() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // sqlSessionFactory对象：一个数据一个。不要频繁创建该对象。
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        /*// 通过sqlSessionFactory对象可以开启多个会话。
        // 会话1
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        sqlSession1.insert("car.insertCar");
        sqlSession1.commit();
        sqlSession1.close(); // 因为要测试连接池的效果，关闭是需要的。别忘了，要不然测不出来。

        // 会话2
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        sqlSession2.insert("car.insertCar");
        sqlSession2.commit();
        sqlSession2.close();*/

        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("car.insertCar");
            // 不要关闭。
        }
    }

    @Test
    public void testEnvironment() throws Exception {
        // 获取SqlSessionFactory对象（采用默认的方式获取）
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 这种方式就是获取的默认环境
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行SQL语句
        sqlSession.insert("car.insertCar");
        sqlSession.commit();
        sqlSession.close();

        // 这种方式就是通过环境id来使用指定的环境
        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "powernodeDB");
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        // 执行SQL语句
        sqlSession1.insert("car.insertCar");
        sqlSession1.commit();
        sqlSession1.close();
    }
}
