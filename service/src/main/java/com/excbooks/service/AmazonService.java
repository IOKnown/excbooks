package com.excbooks.service;

import com.excbooks.dto.Image;
import com.excbooks.dto.ImageType;

import java.io.File;

public interface AmazonService {
    public String addS3Object(File file, ImageType type);
    public void deleteS3Object(Image img);
    public File getS3Object(Image img);
}
