package com.powernode.junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试类
 * @author 动力节点
 * @version 1.0
 * @since 1.0
 */
public class MathServiceTest { // 名字规范：你要测试的类名+Test

    // 单元测试方法写多少个。
    // 一般是一个业务方法对应一个测试方式。
    // 测试方法的规范: public void testXxxx(){}
    // 测试方法的方法名：以test开始。假设测试的方法是sum，这个测试方法名：testSum
    // @Test注解非常重要，被这个注解标注的方法就是一个单元测试方法。
    @Test
    public void testSum(){
        // 单元测试中有两个重要的概念：
        // 一个是：实际值（被测试的业务方法的真正执行结果）
        // 一个是：期望值（执行了这个业务方法之后，你期望的执行结果是多少）
        MathService mathService = new MathService();
        // 获取实际值
        int actual = mathService.sum(1, 2);
        // 期望值
        //int expected = 3;
        int expected = 30;
        // 加断言进行测试
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSub(){
        MathService mathService = new MathService();
        // 实际值
        int actual = mathService.sub(10, 5);
        // 期望值
        int expected = 5;
        // 添加断言机制
        Assert.assertEquals(expected, actual);
    }

}













