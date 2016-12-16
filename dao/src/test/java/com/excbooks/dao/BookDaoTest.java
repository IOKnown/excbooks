package com.excbooks.dao;

import com.excbooks.dto.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;

public class BookDaoTest {
    private static final Logger LOGGER = LogManager.getLogger(BookDaoTest.class);

    @Autowired
    BookDao bookDao;

    @Test
    @Transactional(readOnly = true)
    public void selectTest(){
        LOGGER.info("++++++++++++FindById++++++++++++++++++++++++++++++");
        Book book = bookDao.findOne(new BigInteger("1"));
        assertEquals(book.getId(),new BigInteger("1"));
        assertEquals(book.getName(),"Book of life");
        LOGGER.info(book.toString());
        LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
