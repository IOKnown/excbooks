package com.excbooks.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "bid")
    @SequenceGenerator(name="id_book", sequenceName="book_bid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_book")
    private BigInteger id;

    @Column(name = "bname")
    private String name;

    @Column(name = "bdesc")
    private String describe;

    @Column(name = "price")
    private Integer price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books" )
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User owner;

    public Book() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String decr) {
        this.describe = decr;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                "| name='" + name + '\'' +
                "| describe='" + describe + '\'' +
                '}';
    }
}
