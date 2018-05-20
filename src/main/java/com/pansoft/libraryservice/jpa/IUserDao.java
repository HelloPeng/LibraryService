package com.pansoft.libraryservice.jpa;

import com.pansoft.libraryservice.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：21:08
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public interface IUserDao extends JpaRepository<UserDao, String> {

    UserDao findByUsername(String username);
}
