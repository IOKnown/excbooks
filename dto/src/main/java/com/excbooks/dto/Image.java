package com.excbooks.dto;


import javax.persistence.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
@Table(name = "image")
public class Image {
    private final int INDEX_OF_START_NAME = 9;
    private final String START_OF_URL = "https://s3.eu-central-1.amazonaws.com/ioknown/";
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
        if(imgURL.contains(START_OF_URL)){
            this.imgURL = imgURL;
        }else {
            this.imgURL = START_OF_URL + imgURL;
        }
    }

    public String getImgOldName() {
        return imgOldName;
    }

    public void setImgOldName(String imgOldName) {
        this.imgOldName = imgOldName;
    }

    public String getKeyWithFolder() {
        String key = null;
        if (this.imgURL != null) {
            URL url = null;
            try {
                url = new URL(imgURL);
                key = url.getPath().substring(INDEX_OF_START_NAME);
            } catch (MalformedURLException e) {return null;}
            return key;
        } else {
            return null;
        }
    }

    public String getKey(){
        String temp = getKeyWithFolder();
        return temp.substring(temp.indexOf('/')+1);
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
