package com.excbooks.service;

import com.excbooks.dto.Image;
import com.excbooks.service.impl.AmazonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AmazonServiceTest {

    @InjectMocks
    AmazonService amazonService = new AmazonServiceImpl();

    @Mock
    ImageService imageService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void amazonTestCRUD() throws IOException {
        when(imageService.nextImgId()).thenReturn(3);
        File file = new File(this.getClass().getClassLoader().getResource("image2.png").getFile());
        String imgURL = amazonService.addS3Object(file, FileType.book);
        Image img = new Image();
        img.setImgURL(imgURL);
        File file1 = amazonService.getS3Object(img);
        assertEquals(file1.length(),file.length());
        assertEquals(file1.getName(),img.getKey());
        amazonService.deleteS3Object(img);
        if (file.delete()){
            return;
        }else {
            throw new IOException();
        }
    }


}
