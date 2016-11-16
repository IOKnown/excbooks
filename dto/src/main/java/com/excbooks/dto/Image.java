package com.excbooks.dto;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "imgid")
    private BigInteger imgId;

    @Column(name = "imgurl")
    private String imgURL;

    @Column(name = "img_oldname")
    private String imgOldName;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

    public Image() {
    }

    public BigInteger getImgId() {
        return imgId;
    }

    public void setImgId(BigInteger imgId) {
        this.imgId = imgId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getImgOldName() {
        return imgOldName;
    }

    public void setImgOldName(String imgOldName) {
        this.imgOldName = imgOldName;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imgId=" + imgId +
                "| imgURL='" + imgURL + '\'' +
                "| imgOldName='" + imgOldName + '\'' +
                '}';
    }
}
