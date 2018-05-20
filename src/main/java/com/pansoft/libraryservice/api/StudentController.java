package com.pansoft.libraryservice.api;

import com.alibaba.fastjson.JSON;
import com.pansoft.libraryservice.dao.QStudentDao;
import com.pansoft.libraryservice.dao.StudentDao;
import com.pansoft.libraryservice.jpa.IStudentDao;
import com.pansoft.libraryservice.utils.Dump;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月17日
 * 时间：19:48
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
@RestController
public class StudentController {


    private JPAQueryFactory jpa;
    private IStudentDao iStudentDao;

    @Autowired
    public StudentController(JPAQueryFactory jpa, IStudentDao iStudentDao) {
        this.jpa = jpa;
        this.iStudentDao = iStudentDao;
    }

    @PostMapping("/api/student/add")
    public Dump addStudent(@RequestBody String params) {
        List<StudentDao> studentList = JSON.parseArray(params, StudentDao.class);
        if (studentList != null && !studentList.isEmpty()) {
            iStudentDao.saveAll(studentList);
            return Dump.success("添加成功", true);
        }
        return Dump.fail("添加的数据不可为空");
    }

    @PostMapping("/api/student/update")
    public Dump updateStudent(@RequestParam("student") StudentDao student) {
        if (student.getOid() != null) {
            QStudentDao studentDao = QStudentDao.studentDao;
            List<Long> fetch = jpa.select(studentDao.count())
                    .from(studentDao)
                    .where(studentDao.oid.eq(student.getOid()))
                    .fetch();
            if (fetch != null && !fetch.isEmpty()) {
                iStudentDao.saveAndFlush(student);
            }
        }
        return Dump.fail("未找到指定的学生信息");
    }

}
