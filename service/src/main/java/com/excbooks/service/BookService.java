package com.excbooks.service;


import com.excbooks.dto.Book;

import java.math.BigInteger;

public interface BookService {
    public Book findById(BigInteger id);
}
