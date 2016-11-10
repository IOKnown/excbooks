package com.excbooks.dao;

import com.excbooks.model.User;
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
public class UserDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoTest.class);

    @Autowired
    UserDao userDao;

    @Test
    @Transactional(readOnly = true)
    public void selectTest(){
        LOGGER.info("++++++++++++FindById++++++++++++++++++++++++++++++");
        User user = userDao.findOne(new BigInteger("1"));
        assertEquals(user.getId(),new BigInteger("1"));
        assertEquals(user.getUsername(),"user");
        LOGGER.info(user.toString());
        LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
