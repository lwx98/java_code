package com.powernode.bank.utils;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 工具类：可以动态的生成DAO的实现类。（或者说可以动态生成DAO的代理类）
 * 注意注意注意注意注意！！！！！！：
 *      凡是使用GenerateDaoProxy的，SQLMapper.xml映射文件中namespace必须是dao接口的全名，id必须是dao接口中的方法名。
 * @author 动力节点
 * @version 1.0
 * @since 1.0
 */
public class GenerateDaoProxy { // GenerateDaoProxy是mybatis框架的开发者写的。

    /**
     * 生成dao接口实现类，并且将实现类的对象创建出来并返回。
     * @param daoInterface dao接口
     * @return dao接口实现类的实例化对象。
     */
    public static Object generate(SqlSession sqlSession, Class daoInterface){
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类(com.powernode.bank.dao.AccountDao --> com.powernode.bank.dao.AccountDaoProxy)
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy"); // 实际本质上就是在内存中动态生成一个代理类。
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中所有的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            // method是接口中的抽象方法
            // 将method这个抽象方法进行实现
            try {
                // Account selectByActno(String actno);
                // public Account selectByActno(String actno){ 代码; }
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 需要方法的形式参数列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" ");
                    methodCode.append("arg").append(i);
                    if(i != parameterTypes.length - 1){
                        methodCode.append(",");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                // 需要方法体当中的代码
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.powernode.bank.utils.SqlSessionUtil.openSession();");
                // 需要知道是什么类型的sql语句
                // sql语句的id是框架使用者提供的，具有多变性。对于我框架的开发人员来说。我不知道。
                // 既然我框架开发者不知道sqlId，怎么办呢？mybatis框架的开发者于是就出台了一个规定：凡是使用GenerateDaoProxy机制的。
                // sqlId都不能随便写。namespace必须是dao接口的全限定名称。id必须是dao接口中方法名。
                String sqlId = daoInterface.getName() + "." + method.getName();
                System.out.println( "sqlId======================" + sqlId);
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {

                }
                if (sqlCommandType == SqlCommandType.DELETE) {

                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\""+sqlId+"\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    String returnType = method.getReturnType().getName();
                    methodCode.append("return ("+returnType+")sqlSession.selectOne(\""+sqlId+"\", arg0);");
                }

                methodCode.append("}");
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        // 创建对象
        Object obj = null;
        try {
            Class<?> clazz = ctClass.toClass();
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
