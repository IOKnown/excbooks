package com.excbooks.service.impl;


import com.excbooks.dao.UserDao;
import com.excbooks.dto.User;
import com.excbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional()
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findById(BigInteger id) {
        return userDao.findOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(User user) throws Exception{
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
        }catch (Exception e){
            //TODO logger
            throw e;
        }

    }


}
