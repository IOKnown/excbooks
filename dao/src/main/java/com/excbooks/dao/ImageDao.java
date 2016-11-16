package com.excbooks.dao;


import com.excbooks.dto.Image;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ImageDao extends CrudRepository<Image, BigInteger> {
}
