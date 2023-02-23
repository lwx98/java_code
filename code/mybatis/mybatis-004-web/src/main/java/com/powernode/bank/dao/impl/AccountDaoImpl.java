package com.powernode.bank.dao.impl;

import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {

    @Override
    public Account selectByActno(String arg0) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //Account account = (Account) sqlSession.selectOne("account.selectByActno", arg0);
        //return account;
        return sqlSession.selectOne("account.selectByActno", arg0);
    }

    @Override
    public int updateByActno(Account arg0) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //int count = sqlSession.update("account.updateByActno", act);
        //return count;
        return sqlSession.update("account.updateByActno", arg0);
    }
}

