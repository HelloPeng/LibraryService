package com.pansoft.libraryservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 作者：lvzhp
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月20日
 * 时间：15:30
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
@Entity
@Table(name ="b_Book")
@GenericGenerator(name = "alicomm-uuid", strategy = "uuid")
public class BookDao {
    @Id
    @Column(name = "b_Oid")
    @GeneratedValue(generator = "alicomm-uuid")
    private String oid;
    @Column(name = "b_Name")
    private String name;
    @Column(name = "b_Publish")
    private String publish;//出版社
    @Column(name = "b_Image")
    private String image;//图书封面
    @Column(name = "b_Author")
    private String author;//作者
    @Column(name = "b_PublishDate")
    private String publishDate;//出版时间
    @Column(name = "b_Sn")
    private String sn;//图书编号
    @Column(name = "b_Type")
    private String type;//图书类型

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

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
