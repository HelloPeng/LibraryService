package com.pansoft.libraryservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：13:44
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
@Entity
@Table(name = "u_Student")
@GenericGenerator(name = "alicomm-uuid", strategy = "uuid")
public class StudentDao {

    @Id
    @Column(name = "u_Oid")
    @GeneratedValue(generator = "alicomm-uuid")
    private String oid;
    @Column(name = "u_Name")
    private String name;
    @Column(name = "u_Password")
    private String password;
    @Column(name = "u_College")
    private String college;//学院名称
    @Column(name = "u_Major")
    private String major;//专业
    @Column(name = "u_Classes")
    private String classes;//班级名称
    @Column(name = "u_Sex")
    private String sex;//1:男/0：女
    @Column(name = "u_Sn")
    private String sn;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
