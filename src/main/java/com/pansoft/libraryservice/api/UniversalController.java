package com.pansoft.libraryservice.api;

import com.pansoft.libraryservice.base.BaseController;
import com.pansoft.libraryservice.dao.*;
import com.pansoft.libraryservice.jpa.IUserDao;
import com.pansoft.libraryservice.retdata.BookBorrowListBean;
import com.pansoft.libraryservice.retdata.StudentBorrowListBean;
import com.pansoft.libraryservice.utils.Dump;
import com.pansoft.libraryservice.utils.MD5Utils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：17:49
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
@RestController
public class UniversalController extends BaseController {

    private JPAQueryFactory jpa;
    private final IUserDao mUserDao;

    @Autowired
    public UniversalController(JPAQueryFactory jpa, IUserDao iUserDao) {
        this.jpa = jpa;
        mUserDao = iUserDao;
    }

    @GetMapping("/api/base/check")
    public Dump checkApi() {
        QUserDao userDao = QUserDao.userDao;
        List<UserDao> admin = jpa.selectFrom(userDao)
                .where(userDao.username.eq("admin"))
                .fetch();
        if (admin == null || admin.isEmpty()) {
            UserDao userBean = new UserDao();
            userBean.setUsername("admin");
            userBean.setPassword(MD5Utils.MD5("admin"));
            userBean.setCreateTime(sf.format(new Date()));
            userBean.setUserType("1");
            UserDao userBean2 = new UserDao();
            userBean2.setUsername("201304140016");
            userBean2.setPassword(MD5Utils.MD5("123"));
            userBean2.setCreateTime(sf.format(new Date()));
            userBean2.setUserType("2");
            mUserDao.save(userBean);
            mUserDao.save(userBean2);
        }
        return Dump.success(true);
    }


    @GetMapping("/api/search")
    public Dump searchData(@RequestParam("key") String key,
                           @RequestParam("type") String type) {
        if ("100".equals(type)) {
            QBookDao bookDao = QBookDao.bookDao;
            List<BookDao> searchData = jpa.selectFrom(bookDao)
                    .where(bookDao.name.contains(key))
                    .fetch();
            return Dump.success(searchData);
        } else if ("110".equals(type)) {
            QStudentDao studentDao = QStudentDao.studentDao;
            List<StudentDao> searchData = jpa.selectFrom(studentDao)
                    .where(studentDao.name.like(key))
                    .fetch();
            return Dump.success(searchData);
        }
        return Dump.fail("未查询到数据");
    }

    @GetMapping("/api/book_borrow")
    public Dump getStudentListsForBookId(@RequestParam("bookId") String bookId) {
        QBorrowRecordDao borrowRecordDao = QBorrowRecordDao.borrowRecordDao;
        QStudentDao studentDao = QStudentDao.studentDao;
        List<BookBorrowListBean> data = jpa.select(
                Projections.fields(BookBorrowListBean.class,
                        borrowRecordDao.recordTime.as("borrowTime"),
                        studentDao.name.as("studentName")))
                .from(borrowRecordDao)
                .leftJoin(studentDao).on(studentDao.oid.eq(borrowRecordDao.studentOid))
                .where(borrowRecordDao.bookOid.eq(bookId))
                .fetch();
        return Dump.success("查询成功", data);
    }

    @GetMapping("/api/student_borrow")
    public Dump getBookListsForStudentId(@RequestParam("studentId") String studentId) {
        QBorrowRecordDao borrowRecordDao = QBorrowRecordDao.borrowRecordDao;
        QBookDao bookDao = QBookDao.bookDao;
        List<StudentBorrowListBean> data = jpa.select(
                Projections.fields(StudentBorrowListBean.class,
                        borrowRecordDao.recordTime.as("borrowTime"),
                        bookDao.name.as("bookName")))
                .from(borrowRecordDao)
                .leftJoin(bookDao).on(bookDao.oid.eq(borrowRecordDao.studentOid))
                .where(borrowRecordDao.studentOid.eq(studentId))
                .fetch();
        return Dump.success("查询成功", data);
    }

    @PostMapping("/api/login")
    public Dump login(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        UserDao userBean = mUserDao.findByUsername(username);
        if (userBean == null) {
            return Dump.fail("未找到该用户，请注册后登录");
        }
        password = MD5Utils.MD5(password);
        String dbPassword = userBean.getPassword();
        if (!password.equals(dbPassword)) {
            return Dump.fail("密码输入错误");
        }
        userBean.setLoginTime(sf.format(new Date()));
        mUserDao.flush();
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("userOid", userBean.getOid());
        returnMap.put("userType", userBean.getUserType());
        return Dump.success("登录成功", returnMap);
    }

    @PostMapping("/api/changepassword")
    @Transactional(rollbackFor = Exception.class)
    public Dump changePassword(@RequestParam("userId") String userId,
                               @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword) {
        QUserDao qUserDao = QUserDao.userDao;
        UserDao userDao = jpa.selectFrom(qUserDao)
                .where(qUserDao.oid.eq(userId))
                .fetchOne();
        if (MD5Utils.MD5(oldPassword).equals(userDao.getPassword())) {
            long execute = jpa.update(qUserDao)
                    .set(qUserDao.password, MD5Utils.MD5(newPassword))
                    .where(qUserDao.oid.eq(userId))
                    .execute();
            if (execute > 0) {
                return Dump.success("密码修改成功");
            }
        }
        return Dump.fail("原密码输入错误");
    }
}
