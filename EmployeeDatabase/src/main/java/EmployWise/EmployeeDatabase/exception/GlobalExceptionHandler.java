package EmployWise.EmployeeDatabase.exception;

import EmployWise.EmployeeDatabase.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // Extract field errors from the exception
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        // Construct error message from field errors
        String errorMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        // Create ErrorResponse object
        ErrorResponse errorResponse = new ErrorResponse(errorMessage,false);
        // Return error response with BAD_REQUEST status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle IllegalStateException
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity <ErrorResponse> handleIllegalStateException(IllegalStateException ex){
        // Create ErrorResponse object with message from the exception
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), false);
        // Return error response with INTERNAL_SERVER_ERROR status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse("please provide valid inputs", false);
        // Return error response with BAD_REQUEST status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        ErrorResponse errorResponse = new ErrorResponse("No employee found with provided id", false);
        // Return error response with NOT_FOUND status
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse response = new ErrorResponse("Invalid request data. Please check your request body.", false);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorResponse> handlePropertyReferenceException(PropertyReferenceException ex) {
        ErrorResponse response = new ErrorResponse("Check your inputs", false);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
