package pl.gozderapatryk.userservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.gozderapatryk.userservice.dto.response.ErrorResponseDto;
import pl.gozderapatryk.userservice.exception.UserAlreadyExistsException;
import pl.gozderapatryk.userservice.exception.UserNotFoundException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlingController {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.debug(ex.getMessage());
        return ErrorResponseDto.builder()
                .path(request.getRequestURI())
                .message("Validation problems - check details.")
                .details(ex.getBindingResult().getFieldErrors().stream().collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet()))))
                .build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDto handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        log.debug(ex.getMessage());
        return ErrorResponseDto.builder()
                .path(request.getRequestURI())
                .message("Validation problems - check details.")
                .details(ex.getConstraintViolations().stream().map(constraintViolation -> {
                    var field = constraintViolation.getPropertyPath().toString().split("\\.")[constraintViolation.getPropertyPath().toString().split("\\.").length - 1];
                    return new FieldError(constraintViolation.getRootBeanClass().getName(), field, constraintViolation.getMessage());
                }).collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet()))))
                .build();
    }

    @ExceptionHandler(value = {
            UserNotFoundException.class,
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFoundException(RuntimeException ex, HttpServletRequest request) {
        log.debug(ex.getMessage());
        return ErrorResponseDto.builder()
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {
            UserAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleConflictException(RuntimeException ex, HttpServletRequest request) {
        log.debug(ex.getMessage());
        return ErrorResponseDto.builder()
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ErrorResponseDto.builder()
                .message("INTERNAL SERVER ERROR")
                .path(request.getRequestURI())
                .build();
    }
}
