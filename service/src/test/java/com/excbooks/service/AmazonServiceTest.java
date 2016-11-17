package com.excbooks.service;

import com.excbooks.dto.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class , loader = AnnotationConfigContextLoader.class)
public class AmazonServiceTest {

    @Autowired
    private AmazonService amazonService;

    @Test
    public void amazonTestCRUD(){
        File file = new File(this.getClass().getClassLoader().getResource("image2.png").getFile());
        String imgURL = amazonService.addObject(file, FileType.book);
        Image img = new Image();
        img.setImgURL(imgURL);
        File file1 = amazonService.getObject(img);
        assertEquals(file1.length(),file.length());
        amazonService.deleteObject(img);
    }
}
