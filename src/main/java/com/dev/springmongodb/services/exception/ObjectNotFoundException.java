package com.dev.springmongodb.services.exception;

import com.dev.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String msg) {
        super(msg); //repassa para a super classe
    }



}
