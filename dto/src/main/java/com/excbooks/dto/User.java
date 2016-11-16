package com.excbooks.dto;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name =  "uid")
    @SequenceGenerator(name="id_user", sequenceName="users_uid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_user")
    private BigInteger id;

    @Column(name = "nfirst")
    private String firstName;

    @Column(name = "nsecond")
    private String secondName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "acbalance")
    private Integer balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Book> books;

    @OneToOne
    @JoinColumn(name = "imgid")
    private Book book;

    public User() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "| firstName='" + firstName + '\'' +
                "| secondName='" + secondName + '\'' +
                "| email='" + email + '\'' +
                "| password='" + password + '\'' +
                "| balance=" + balance +
                '}';
    }
}
