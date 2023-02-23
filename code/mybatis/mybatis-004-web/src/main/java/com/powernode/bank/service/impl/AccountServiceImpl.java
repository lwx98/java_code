package com.powernode.bank.service.impl;

import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.dao.impl.AccountDaoImpl;
import com.powernode.bank.exceptions.MoneyNotEnoughException;
import com.powernode.bank.exceptions.TransferException;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.service.AccountService;
import com.powernode.bank.utils.GenerateDaoProxy;
import com.powernode.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService {

    //private AccountDao accountDao = new AccountDaoImpl();

    // 这是咱们自己封装的。
    //private AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);

    // 在mybatis当中，mybatis提供了相关的机制。也可以动态为我们生成dao接口的实现类。（代理类：dao接口的代理）
    // mybatis当中实际上采用了代理模式。在内存中生成dao接口的代理类，然后创建代理类的实例。
    // 使用mybatis的这种代理机制的前提：SqlMapper.xml文件中namespace必须是dao接口的全限定名称，id必须是dao接口中的方法名。
    // 怎么用？代码怎么写？AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
    private final AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {

        // 添加事务控制代码
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 1. 判断转出账户的余额是否充足(select)
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            // 2. 如果转出账户余额不足，提示用户
            throw new MoneyNotEnoughException("对不起，余额不足！");
        }
        // 3. 如果转出账户余额充足，更新转出账户余额(update)
        // 先更新内存中java对象account的余额
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        int count = accountDao.updateByActno(fromAct);

        // 模拟异常
        /*String s = null;
        s.toString();*/

        // 4. 更新转入账户余额(update)
        count += accountDao.updateByActno(toAct);
        if (count != 2) {
            throw new TransferException("转账异常，未知原因");
        }

        // 提交事务
        sqlSession.commit();
        // 关闭事务
        SqlSessionUtil.close(sqlSession);
    }


}
