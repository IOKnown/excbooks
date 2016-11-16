package com.excbooks.service;


import com.excbooks.dto.User;
import java.math.BigInteger;

public interface UserService{
    public User findById(BigInteger id);
}
