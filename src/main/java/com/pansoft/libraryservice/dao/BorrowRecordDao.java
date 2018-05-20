package com.pansoft.libraryservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：15:23
 * 版本：v1.0.0
 * 类描述：借阅记录
 * 修改时间：
 */
@Entity
@Table(name = "d_BorrowRecordDao")
@GenericGenerator(name = "alicomm-uuid", strategy = "uuid")
public class BorrowRecordDao {
    @Id
    @Column(name = "u_Oid")
    @GeneratedValue(generator = "alicomm-uuid")
    private String oid;
    @Column(name = "d_BookOid")
    private String bookOid;//借阅图书的id
    @Column(name = "u_StudentOid")
    private String studentOid;//借阅人的id
    @Column(name = "u_BorrowStatus")
    private int borrowStatus;//借阅状态 1:已归还
    @Column(name = "u_RecordTime")
    private String recordTime;//记录时间
    @Column(name = "u_ReturnDate")
    private String returnDate;//归还日期（如果借阅状态为已借阅则有此时间）
    @Column(name = "u_ActualReturnDate")
    private String actualReturnDate;//时间归还时间

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBookOid() {
        return bookOid;
    }

    public void setBookOid(String bookOid) {
        this.bookOid = bookOid;
    }

    public String getStudentOid() {
        return studentOid;
    }

    public void setStudentOid(String studentOid) {
        this.studentOid = studentOid;
    }

    public int getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(int borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(String actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
}
