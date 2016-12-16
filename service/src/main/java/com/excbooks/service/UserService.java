package com.excbooks.service;


import com.excbooks.dto.User;
import java.math.BigInteger;

public interface UserService{
    User findById(BigInteger id);
    User findByEmail(String Email);
    void save(User user) throws Exception;
}
