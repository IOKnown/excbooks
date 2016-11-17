package com.excbooks.service;

import com.excbooks.dto.Image;

import java.io.File;

public interface AmazonService {
    public String addObject(File file, FileType type);
    public void deleteObject(Image img);
    public File getObject(Image img);
}
