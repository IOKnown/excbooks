package com.excbooks.dao;

import com.excbooks.model.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserDao extends CrudRepository<User, BigInteger> {

}
