package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.StudentMapper;
import com.powernode.mybatis.pojo.Student;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代理模式：
 * 代理对象 链家
 * 代理方法 找房子
 * 目标对象 我
 * 目标方法 找房子
 */
public class StudentMapperTest {

    @Test
    public void testSelectByNameAndSex2() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // mapper实际上指向了代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // mapper是代理对象
        // selectByNameAndSex2是代理方法
        List<Student> students = mapper.selectByNameAndSex2("张三", '男');
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void testSelectByNameAndSex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndSex("张三", '男');
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByPOJO() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        // POJO对象
        Student student = new Student();
        student.setName("张飞");
        student.setAge(50);
        student.setSex('女');
        student.setBirth(new Date());
        student.setHeight(10.0);

        mapper.insertStudentByPOJO(student);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("姓名", "赵六");
        map.put("年龄", 20);
        map.put("身高", 1.81);
        map.put("性别", '男');
        map.put("生日", new Date());

        mapper.insertStudentByMap(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectBySex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        // char --> Character
        Character sex = Character.valueOf('男');
        List<Student> students = mapper.selectBySex(sex);

        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    // java.util.Date java.sql.Date，他们都是简单类型。
    @Test
    public void testSelectByBirth() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse("1980-10-11");

        List<Student> students = mapper.selectByBirth(birth);

        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByName("李四");
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }
}
