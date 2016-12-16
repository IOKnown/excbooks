package com.excbooks.controller.validator;

import com.excbooks.dto.Message;
import com.excbooks.dto.MessageType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@ControllerAdvice
public class ControllerValidaionHendler {

    private Properties resourcesMessages;

    private static final Logger LOGGER = LogManager.getLogger(ControllerValidaionHendler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return processFieldError(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message processValidationError(HttpMessageNotReadableException ex) {
        return new Message(getMessageText("body.empty"),MessageType.ERROR);
    }

    private Message processFieldError(FieldError error) {
        Message message = null;
        if (error != null) {
            message = new Message(getMessageText(error.getDefaultMessage()), MessageType.ERROR);
        }
        return message;
    }

    private String getMessageText(String mesKey){
        if(resourcesMessages == null){
            resourcesMessages = new Properties();
            try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("messages.properties")){
                resourcesMessages.load(inputStream);

            }catch (IOException e) {
                LOGGER.error("File messages.properties nit found", e);
                return "undefined error";
            }
        }
        return resourcesMessages.getProperty(mesKey);
    }
}

