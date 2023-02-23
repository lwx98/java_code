package org.god.ibatis.core;

import java.sql.Connection;

/**
 * MANAGED事务管理器。（godbatis对这个类就不再实现了。）
 *
 * @author 动力节点
 * @version 1.0
 * @since 1.0
 */
public class ManagedTransaction implements Transaction{

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public void openConnection() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
