package com.excbooks.service.impl;

import com.excbooks.dto.Book;
import com.excbooks.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    @Transactional(readOnly = true)
    public Book findById(BigInteger id) {
        return null;
    }
}
