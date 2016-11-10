package com.excbooks.model;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "imgid")
    private BigInteger imgId;

    @Column(name = "img")
    private Byte[] img;

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

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imgId=" + imgId +
                "| img=" + Arrays.toString(img) +
                '}';
    }
}
