package org.god.ibatis.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC事务管理器（godbatis框架目前只对JdbcTransaction进行实现。）
 * @author 动力节点
 * @version 1.0
 * @since 1.0
 */
public class JdbcTransaction implements Transaction{

    /**
     * 数据源属性
     * 经典的设计：面向接口编程。
     */
    private final DataSource dataSource;

    /**
     * 自动提交标志
     * true表示自动提交
     * false表示不采用自动提交
     */
    private final boolean autoCommit;

    /**
     * 连接对象
     */
    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * 创建事务管理器对象
     * @param dataSource
     * @param autoCommit
     */
    public JdbcTransaction(DataSource dataSource, boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection(){
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                // 开启事务
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
