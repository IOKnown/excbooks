package com.excbooks.dao;

import com.excbooks.dto.Image;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigInteger;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class , loader = AnnotationConfigContextLoader.class)
public class ImageDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(ImageDao.class);

    @Autowired
    private ImageDao imageDao;

    @Test
    public void getByIdTest(){
        LOGGER.info("++++++++++++FindById++++++++++++++++++++++++++++++");
        Image image = imageDao.findOne(new BigInteger("1"));
        assertEquals(image.getImgId(),new BigInteger("1"));
        assertEquals(image.getImgURL(),"https://s3.eu-central-1.amazonaws.com/ioknown/book/GbdCUTc7ksE.jpg");
        LOGGER.info(image.toString());
        LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void nextImgIdTest(){
        LOGGER.info("++++++++++++FindById++++++++++++++++++++++++++++++");
        int currImgId = imageDao.nextImageId();
        assertEquals(3,currImgId);
        LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
