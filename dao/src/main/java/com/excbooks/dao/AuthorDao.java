package com.excbooks.dao;

import com.excbooks.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface AuthorDao extends CrudRepository<Author, BigInteger> {
}
