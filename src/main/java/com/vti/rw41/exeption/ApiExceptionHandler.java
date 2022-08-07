package com.vti.rw41.exeption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException exception) {
        Map<String, String> messages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::createFieldErrorMessage)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s, s2) -> s + ", " + s2));
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception exception, HttpServletRequest request) {
        if (exception instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
        String message = messageSource.getMessage(
                "system.error",
                new Object[]{},
                "system.error",
                request.getLocale());
        exception.printStackTrace();
        return new ResponseEntity<>(
                Map.of("error", message),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleLoginException(Exception exception, HttpServletRequest request) {

        String message = messageSource.getMessage(
                "AccessDeniedException.error",
                new Object[]{},
                "AccessDeniedException.error",
                request.getLocale());
        return new ResponseEntity<>(Map.of("error", message), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException exception, HttpServletRequest request) {

        String message = messageSource.getMessage(
                exception.getMessageCode(), // message code (trước dấu = trong file properties)
                new Object[]{}, // tham số msg
                exception.getMessageCode(), // nếu không thấy msg thì lấy giá trị mặ định
                request.getLocale() // ngôn ngữ của msg
        );

        return new ResponseEntity<>(
                Map.of("error", message),
                HttpStatus.BAD_REQUEST);
    }

    private Map.Entry<String, String> createFieldErrorMessage(FieldError fieldError) {
        return Map.entry(fieldError.getField(), Objects.requireNonNull(fieldError.getDefaultMessage()));

    }
}