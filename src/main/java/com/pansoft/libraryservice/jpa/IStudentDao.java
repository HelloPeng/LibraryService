package com.pansoft.libraryservice.jpa;

import com.pansoft.libraryservice.dao.StudentDao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：17:52
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public interface IStudentDao extends JpaRepository<StudentDao, String> {
}
