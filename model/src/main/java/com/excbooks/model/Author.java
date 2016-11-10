package com.excbooks.model;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @Column(name = "aid")
    @SequenceGenerator(name = "id_author", sequenceName = "author_aid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_author")
    private BigInteger id;

    @Column(name = "aname")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "authorid", referencedColumnName = "aid"),
            inverseJoinColumns = @JoinColumn(name = "bookid", referencedColumnName = "bid"))
    private Set<Book> books;

    public Author() {
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                "| name='" + name + '\'' +
                '}';
    }
}
