package com.excbooks.controller.validator;


import com.excbooks.dto.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password.empty", "password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","firstName.empty", "firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondName","secondName.empty", "secondName.empty");
        if(!isEmailValid(user.getEmail())){
            errors.reject("email.notValid");
        }
        if(!isUrlValid(user.getImageUrl())){
            errors.reject("url.notValid");
        }
    }

    private boolean isEmailValid(String email){
        if(email == null ){
            return true;
        }
        InternetAddress internetAddress;
        try {
            internetAddress = new InternetAddress(email);
        } catch (AddressException e) {
            LOGGER.error("error parsing internet address",e);
            return false;
        }
        try {
            internetAddress.validate();
            return true;
        }catch (AddressException e){
            return false;
        }
    }

    private boolean isUrlValid(String url){
        if(url == null){
            return true;
        }
        Pattern pattern = Pattern.compile("((http|https)?://)?\\S+\\.(jpg|png|img)");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

}
