package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CarMapperTest {

    @Test
    public void testSelectById2() throws Exception{
        // 这里只有一个SqlSessionFactory对象。二级缓存对应的就是SqlSessionFactory。
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        // 这行代码执行结束之后，实际上数据是缓存到一级缓存当中了。（sqlSession1是一级缓存。）
        Car car1 = mapper1.selectById2(164L);
        System.out.println(car1);

        // 如果这里不关闭SqlSession1对象的话，二级缓存中还是没有数据的。

        // 如果执行了这行代码，sqlSession1的一级缓存中的数据会放到二级缓存当中。
        sqlSession1.close();

        // 这行代码执行结束之后，实际上数据会缓存到一级缓存当中。（sqlSession2是一级缓存。）
        Car car2 = mapper2.selectById2(164L);
        System.out.println(car2);

        // 程序执行到这里的时候，会将sqlSession1这个一级缓存中的数据写入到二级缓存当中。
        //sqlSession1.close();
        // 程序执行到这里的时候，会将sqlSession2这个一级缓存中的数据写入到二级缓存当中。
        sqlSession2.close();
    }

    /*@Test
    public void testSelectById() throws Exception{
        // 如果要获取不同的SqlSession对象，不能使用以下代码。
        //SqlSession sqlSession = SqlSessionUtil.openSession();

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(164L);
        System.out.println(car1);

        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
        Car car2 = mapper2.selectById(164L);
        System.out.println(car2);

        sqlSession1.close();
        sqlSession2.close();
    }*/


    // 思考：什么时候不走缓存？
    // SqlSession对象不是同一个，肯定不走缓存。
    // 查询条件不一样，肯定也不走缓存。

    // 思考：什么时候一级缓存失效？
    // 第一次DQL和第二次DQL之间你做了以下两件事中的任意一件，都会让一级缓存清空：
    //     1. 执行了sqlSession的clearCache()方法，这是手动清空缓存。
    //     2. 执行了INSERT或DELETE或UPDATE语句。不管你是操作哪张表的，都会清空一级缓存。
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();

        CarMapper mapper1 = sqlSession.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(164L);
        System.out.println(car1);

        // 手动清空一级缓存
        //sqlSession.clearCache();

        // 在这里执行了INSERT DELETE UPDATE中的任意一个语句。并且和表没有关系。
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        mapper.insertClazz(2000, "高三三班");

        CarMapper mapper2 = sqlSession.getMapper(CarMapper.class);
        Car car2 = mapper2.selectById(164L);
        System.out.println(car2);

        sqlSession.commit();
        sqlSession.close();
    }
}
