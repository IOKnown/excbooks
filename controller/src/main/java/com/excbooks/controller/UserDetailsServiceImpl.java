package com.excbooks.controller;

import com.excbooks.dao.UserDao;
import com.excbooks.dto.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);
        if(user == null){
            LOGGER.warn("user by email"+email+"not found");
            throw  new UsernameNotFoundException("user by email "+email+" not found");
        }
        List auths = new ArrayList();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new  org.springframework.security.core.userdetails.User(email,user.getPassword(), auths);
    }
}
