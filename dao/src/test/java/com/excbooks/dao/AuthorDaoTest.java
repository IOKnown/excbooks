package com.excbooks.dao;

import com.excbooks.dto.Author;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class , loader = AnnotationConfigContextLoader.class)
public class AuthorDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(AuthorDaoTest.class);

    @Autowired
    AuthorDao authorDao;

    @Test
    @Transactional(readOnly = true)
    public void selectTest(){
        LOGGER.info("++++++++++++FindById++++++++++++++++++++++++++++++");
        Author author = authorDao.findOne(new BigInteger("1"));
        assertEquals(author.getId(),new BigInteger("1"));
        assertEquals(author.getName(),"ivan author");
        LOGGER.info(author.toString());
        LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
