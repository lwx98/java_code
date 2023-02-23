package com.powernode.bank.dao;

import com.powernode.bank.pojo.Account;

/**
 * 账户的DAO对象。负责t_act表中数据的CRUD.
 * 强调一下：DAO对象中的任何一个方法和业务不挂钩。没有任何业务逻辑在里面。
 * DAO中的方法就是做CRUD的。所以方法名大部分是：insertXXX deleteXXX updateXXX selectXXX
 * @author 动力节点
 * @version 1.0
 * @since 1.0
 */
public interface AccountDao {

    /**
     * 根据账号查询账户信息。
     * @param actno 账号
     * @return 账户信息
     */
    Account selectByActno(String actno);

    /**
     * 更新账户信息
     * @param act  被更新的账户对象
     * @return 1表示更新成功，其他值表示失败。
     */
    int updateByActno(Account act);

}
