package org.god.ibatis.utils;

import java.io.InputStream;

/**
 * godbatis框架提供的一个工具类。
 * 这个工具类专门完成“类路径”中资源的加载。
 * @author 动力节点
 * @since 1.0
 * @version 1.0
 */
public class Resources {
    /**
     * 工具类的构造方法都是建议私有化的。
     * 因为工具类中的方法都是静态的，不需要创建对象就能调用。
     * 为了避免new对象，所有构造方法私有化。
     * 这只是一种编程习惯。
     */
    private Resources(){}

    /**
     * 从类路径当中加载资源。
     * @param resource 放在类路径当中的资源文件。
     * @return 指向资源文件的一个输入流。
     */
    public static InputStream getResourceAsStream(String resource){
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }
}
