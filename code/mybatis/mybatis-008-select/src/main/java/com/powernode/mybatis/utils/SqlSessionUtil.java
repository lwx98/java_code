package com.powernode.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatis工具类
 * @author 动力节点
 * @version 1.0
 * @since 1.0
 */
public class SqlSessionUtil {

    private SqlSessionUtil(){}

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 全局的，服务器级别的，一个服务器当中定义一个即可。
    // 为什么把SqlSession对象放到ThreadLocal当中呢？为了保证一个线程对应一个SqlSession。
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 获取会话对象。
     * @return 会话对象
     */
    public static SqlSession openSession(){
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            // 将sqlSession对象绑定到当前线程上。
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭SqlSession对象(从当前线程中移除SqlSession对象。)
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            // 注意移除SqlSession对象和当前线程的绑定关系。
            // 因为Tomcat服务器支持线程池。也就是说：用过的线程对象t1，可能下一次还会使用这个t1线程。
            local.remove();
        }
    }

}
