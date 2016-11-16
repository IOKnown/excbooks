package com.excbooks.service;

import com.excbooks.dto.Author;
import java.math.BigInteger;

public interface AuthorService {
    public Author findById(BigInteger id);
}
