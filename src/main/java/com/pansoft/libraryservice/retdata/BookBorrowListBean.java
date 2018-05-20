package com.pansoft.libraryservice.retdata;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：19:31
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class BookBorrowListBean {

    private String studentName;//学生名称
    private String borrowTime;//借阅时间

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
