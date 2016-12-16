package com.excbooks.service;

import com.excbooks.dto.Image;
import com.excbooks.dto.ImageType;
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
        File file = new File(this.getClass().getClassLoader().getResource("out1.png").getFile());
        String imgURL = amazonService.addS3Object(file, ImageType.book);
        Image img = new Image();
        img.setImgURL(imgURL);
        File fileReturned = amazonService.getS3Object(img);
        assertEquals(fileReturned.length(),file.length());
        assertEquals(fileReturned.getName(),img.getKey());
        amazonService.deleteS3Object(img);
        if (!fileReturned.delete()){
            throw new IOException();
        }
    }


}
