package com.pansoft.libraryservice.retdata;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：19:47
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class StudentBorrowListBean {

    private String bookName;//图书名称
    private String borrowTime;//借阅时间

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
