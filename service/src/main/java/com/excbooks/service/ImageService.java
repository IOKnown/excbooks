package com.excbooks.service;

import com.excbooks.dto.Image;

import java.math.BigInteger;

public interface ImageService {
    public Image findById(BigInteger id);
    public int nextImgId();
}
