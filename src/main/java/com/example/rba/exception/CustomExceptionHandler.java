package com.example.rba.exception;

import com.example.rba.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ClientNotFoundException.class, ClientTaxNumberInUseException.class, ActiveClientCardRequestException.class})
    ResponseEntity<?> handleClientNotFoundException(RuntimeException e) {
        return buildResponseEntity("1", "101", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        return buildResponseEntity("2", "201", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        ex.getAllErrors().forEach(err -> {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(err.getDefaultMessage());
        });
        return buildResponseEntity("3", "301", sb.toString(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(String errorId, String errorCode, String errorMessage, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId(errorId);
        errorResponse.setCode(errorCode);
        errorResponse.setDescription(errorMessage);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
