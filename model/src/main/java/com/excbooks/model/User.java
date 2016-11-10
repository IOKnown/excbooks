package com.excbooks.model;


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

    @Column(name = "uname")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "acbalance")
    private Integer balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Book> books;

    public User() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "| username='" + username + '\'' +
                "| password='" + password + '\'' +
                "| balance=" + balance +
                '}';
    }
}
