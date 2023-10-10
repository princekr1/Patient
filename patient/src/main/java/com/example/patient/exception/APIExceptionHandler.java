package com.example.patient.exception;

import com.example.patient.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {
// extends ResponseEntityExceptionHandler
    @ExceptionHandler(value= EntityNotFound.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException( Exception ex, WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler(value= Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException( Exception ex, WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException( Exception ex, WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage("Validation Exception : "+ex.getLocalizedMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
