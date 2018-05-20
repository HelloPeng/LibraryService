package com.pansoft.libraryservice.api;

import com.alibaba.fastjson.JSON;
import com.pansoft.libraryservice.dao.BookDao;
import com.pansoft.libraryservice.dao.QBookDao;
import com.pansoft.libraryservice.dao.QStudentDao;
import com.pansoft.libraryservice.dao.StudentDao;
import com.pansoft.libraryservice.jpa.IBookDao;
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
public class BookController {

    private JPAQueryFactory jpa;
    private IBookDao iBookDao;

    @Autowired
    public BookController(JPAQueryFactory jpa, IBookDao iBookDao) {
        this.jpa = jpa;
        this.iBookDao = iBookDao;
    }

    @PostMapping("/api/book/add")
    public Dump addStudent(@RequestBody String params) {
        List<BookDao> bookList = JSON.parseArray(params, BookDao.class);
        if (bookList != null && !bookList.isEmpty()) {
            iBookDao.saveAll(bookList);
            return Dump.success("添加成功", true);
        }
        return Dump.fail("添加的数据不可为空");
    }

    @PostMapping("/api/book/update")
    public Dump updateStudent(@RequestParam("book") BookDao book) {
        if (book.getOid() != null) {
            QBookDao bookDao = QBookDao.bookDao;
            List<Long> fetch = jpa.select(bookDao.count())
                    .from(bookDao)
                    .where(bookDao.oid.eq(book.getOid()))
                    .fetch();
            if (fetch != null && !fetch.isEmpty()) {
                iBookDao.saveAndFlush(book);
            }
        }
        return Dump.fail("未找到指定的学生信息");
    }

}
