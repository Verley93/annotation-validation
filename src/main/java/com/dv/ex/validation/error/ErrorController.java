package com.dv.ex.validation.error;

import com.dv.ex.validation.model.ErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("handleAllExceptions: error=[{}]", ex.getMessage());
        return new ResponseEntity<>(
                ErrorResponseModel.builder()
                        .errorMessage(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getDescription(false))
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.error("handleEntityNotFoundException: error=[{}]", ex.getMessage());
        return new ResponseEntity<>(
                ErrorResponseModel.builder()
                        .errorMessage(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getDescription(false))
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        log.error("handleMethodArgumentNotValid: errors=[{}]", errorMessages);
        return new ResponseEntity<>(
                ErrorResponseModel.builder()
                        .errorMessage(errorMessages.get(0))
                        .timestamp(LocalDateTime.now())
                        .path(request.getDescription(false))
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
