package com.powernode.xml.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {

    @Test
    public void testParseSqlMapperXML() throws Exception {
        SAXReader reader = new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = reader.read(is);
        // 获取namespace
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
        System.out.println(namespace);
        // 获取mapper节点下所有的子节点
        List<Element> elements = mapper.elements();
        // 遍历
        elements.forEach(element -> {
            // 获取sqlId
            String id = element.attributeValue("id");
            System.out.println(id);
            // 获取resultType
            String resultType = element.attributeValue("resultType"); // 没有这个属性的话，会自动返回"null"
            System.out.println(resultType);
            // 获取标签中的sql语句（表示获取标签中的文本内容，而且去除前后空白）
            String sql = element.getTextTrim();
            System.out.println(sql);
            // insert into t_car values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})
            // insert into t_car values(null,?,?,?,?,?)
            // mybaits封装了jdbc。早晚要执行带有?的sql语句。
            // 转换
            String newSql = sql.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(newSql);
        });
    }

    @Test
    public void testParseMyBatisConfigXML() throws Exception {
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 获取输入流
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        // 读XML文件，返回document对象。document对象是文档对象，代表了整个XML文件。
        Document document = reader.read(is);
        // 获取文档当中的根标签
        //Element rootElt = document.getRootElement();
        //String rootEltName = rootElt.getName();
        //System.out.println("根节点的名字：" + rootEltName);

        //获取default默认的环境id
        // xpath是做标签路径匹配的。能够让我们快速定位XML文件中的元素。
        // 以下的xpath代表了：从根下开始找configuration标签，然后找configuration标签下的子标签environments
        String xpath = "/configuration/environments";
        Element environments = (Element) document.selectSingleNode(xpath); // Element是Node类的子类，方法更多，使用更便捷。
        // 获取属性的值
        String defaultEnvironmentId = environments.attributeValue("default");
        //System.out.println("默认环境的id：" + defaultEnvironmentId);
        // 获取具体的环境environment
        xpath = "/configuration/environments/environment[@id='" + defaultEnvironmentId + "']";
        //System.out.println(xpath);
        Element environment = (Element) document.selectSingleNode(xpath);
        // 获取environment节点下的transactionManager节点(Element的element()方法用来获取孩子节点)
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
        System.out.println("事务管理器的类型：" + transactionType);
        // 获取dataSource节点
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据源的类型：" + dataSourceType);
        // 获取dataSource节点下的所有子节点
        List<Element> propertyElts = dataSource.elements();
        // 遍历
        propertyElts.forEach(propertyElt -> {
            String name = propertyElt.attributeValue("name");
            String value = propertyElt.attributeValue("value");
            System.out.println(name + "=" + value);
        });
        // 获取所有的mapper标签
        // 不想从根下开始获取，你想从任意位置开始，获取所有的某个标签，xpath该这样写
        xpath = "//mapper";
        List<Node> mappers = document.selectNodes(xpath);
        // 遍历
        mappers.forEach(mapper -> {
            Element mapperElt = (Element) mapper;
            String resource = mapperElt.attributeValue("resource");
            System.out.println(resource);
        });
    }
}
