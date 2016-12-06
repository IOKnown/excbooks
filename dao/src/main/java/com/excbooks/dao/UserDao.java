package com.excbooks.dao;

import com.excbooks.dto.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserDao extends CrudRepository<User, BigInteger> {
    public User findUserByEmail(String email);
}
