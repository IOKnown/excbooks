package com.excbooks.dao;


import com.excbooks.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface BookDao extends CrudRepository<Book, BigInteger> {

}
