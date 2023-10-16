package com.dev.springmongodb.resources.exception;

import com.dev.springmongodb.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //tratar possiveis erros - aqui que trata e notifica no retorno do request
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) //quando acha a exc realiza esse no lugar:
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){ //exigencia do framework - http..
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(),status.value(),"Não encontrado", e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
