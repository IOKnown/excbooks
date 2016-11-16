package com.excbooks.service.impl;


import com.excbooks.dao.UserDao;
import com.excbooks.dto.User;
import com.excbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User findById(BigInteger id) {
        return userDao.findOne(id);
    }
}
