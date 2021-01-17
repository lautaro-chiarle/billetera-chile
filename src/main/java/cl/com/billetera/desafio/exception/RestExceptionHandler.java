package cl.com.billetera.desafio.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.com.billetera.desafio.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * Método para gestionar los errores de validación en los request.
     * @param ex
     * @return Map<String, String>
     */
    @ExceptionHandler()
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.FORBIDDEN.value(),
                exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(BadCredentialsException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Error en el formato de los datos ingresados");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
