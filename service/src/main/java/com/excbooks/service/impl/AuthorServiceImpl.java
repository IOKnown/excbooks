package com.excbooks.service.impl;

import com.excbooks.dto.Author;
import com.excbooks.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {
    @Transactional(readOnly = true)
    public Author findById(BigInteger id) {
        return null;
    }
}
