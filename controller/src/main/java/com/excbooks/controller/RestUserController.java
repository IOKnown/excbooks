package com.excbooks.controller;

import com.excbooks.controller.validator.UserValidator;
import com.excbooks.dto.Message;
import com.excbooks.dto.MessageType;
import com.excbooks.dto.User;
import com.excbooks.service.UserService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RestUserController {
    private static final Logger LOGGER = LogManager.getLogger(RestUserController.class);

    {
        BasicConfigurator.configure();
    }

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Message> saveUser(@Valid @RequestBody User user){
        User userFromDB = userService.findByEmail(user.getEmail());
        if (userFromDB != null){
            return  new ResponseEntity<>(new Message("User with this email:" +user.getEmail()+" already exist", MessageType.ERROR),
                    HttpStatus.CONFLICT);
        }
        try{
            userService.save(user);
        }catch (Exception e){
            //TODO logger;
            return new ResponseEntity<>(new Message("User not saved in data base", MessageType.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
        return null;
    }

}
