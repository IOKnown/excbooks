package com.excbooks.dao;


import com.excbooks.dto.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ImageDao extends CrudRepository<Image, BigInteger>{
    @Query(value = "SELECT last_value + 1 FROM image_imgid_seq", nativeQuery = true)
    int nextImageId();
}
