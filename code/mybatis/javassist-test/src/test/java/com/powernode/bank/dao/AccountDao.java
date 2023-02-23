package com.powernode.bank.dao;

public interface AccountDao {
    void delete();
    int insert(String actno);
    int update(String actno, Double balance);
    String selectByActno(String actno);
}
