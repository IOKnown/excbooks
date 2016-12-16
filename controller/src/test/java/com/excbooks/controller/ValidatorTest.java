package com.excbooks.controller;


import com.excbooks.controller.validator.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ControllerTestConfig.class , loader = AnnotationConfigContextLoader.class)
public class ValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @Test
    public void emailValidationTest() {
        Class userValidatorClass = userValidator.getClass();
        Class stringClass = String.class;
        String url = "https://s3.eu-central-1.amazonaws.com/ioknown/book/GbdCUTc7ksE.jpg";
        String urlNotValid = "http://www.quizful.net/post/java-reflection-api";
        String text = "Some String";
        try {
            Method method = userValidatorClass.getDeclaredMethod("isUrlValid", stringClass);
            method.setAccessible(true);
            Boolean validUrlResult = (Boolean)method.invoke(userValidator, url);
            Boolean notValidResult = (Boolean)method.invoke(userValidator, urlNotValid);
            Boolean textResult = (Boolean)method.invoke(userValidator, text);
            assertEquals(true, validUrlResult.booleanValue());
            assertEquals(false, notValidResult.booleanValue());
            assertEquals(false, textResult.booleanValue());
        } catch (NoSuchMethodException |InvocationTargetException |IllegalAccessException e) {
            e.printStackTrace();
            assertFalse(true);
            // todo add test logger  to all test method
        }
    }
}