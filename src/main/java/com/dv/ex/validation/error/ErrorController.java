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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
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

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<Object> invalidValues = constraintViolations.stream()
                .map(ConstraintViolation::getInvalidValue)
                .collect(Collectors.toList());
        List<String> errorMessages = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        log.error("handleConstraintViolationException: invalidValues={} errors={}", invalidValues, errorMessages);
        return new ResponseEntity<>(
                ErrorResponseModel.builder()
                        .errorMessage(errorMessages.get(0))
                        .timestamp(LocalDateTime.now())
                        .path(request.getDescription(false))
                        .build(),
                HttpStatus.BAD_REQUEST
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
