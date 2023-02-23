package com.powernode.mybatis.pojo;

import com.powernode.mybatis.mapper.ClazzMapper;

/**
 * 学生信息
 */
public class Student { // Student是多的一方
    private Integer sid;
    private String sname;
    private Clazz clazz; // Clazz是一的一方。
    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Student(Integer sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

    public Student() {
    }
}
