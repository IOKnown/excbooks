package com.excbooks.service.impl;

import com.excbooks.dao.ImageDao;
import com.excbooks.dto.Image;
import com.excbooks.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Transactional(readOnly = true)
    @Override
    public Image findById(BigInteger id) {
        return imageDao.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public int nextImgId() {
        return imageDao.nextImageId();
    }
}
