package com.pansoft.libraryservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：21:06
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
@Entity
@Table(name = "p_User")
@GenericGenerator(name = "alicomm-uuid", strategy = "uuid")
public class UserDao {

    @Id
    @Column(name = "u_oid")
    @GeneratedValue(generator = "alicomm-uuid")
    private String oid;
    @Column(name = "u_CreateTime")
    private String createTime;
    @Column(name = "u_LoginTime")
    private String loginTime;
    @Column(name = "u_Username")
    private String username;
    @Column(name = "u_Password")
    private String password;
    @Column(name = "u_UserType")
    private String userType;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
