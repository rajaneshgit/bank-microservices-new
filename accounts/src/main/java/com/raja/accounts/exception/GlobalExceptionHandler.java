package com.raja.accounts.exception;

import com.raja.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Custom exception handler for validation errors.
     * <p>
     * This method handles a MethodArgumentNotValidException, which is thrown when a request body contains invalid values.
     * It then returns a ResponseEntity containing a map of field names to validation error messages.
     * The ResponseEntity has a status of BAD_REQUEST.
     * </p>
     *
     * @param ex      validation exception
     * @param headers headers
     * @param status  status
     * @param request request
     * @return ResponseEntity containing validation errors
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    /**
     * A global exception handler for any unexpected exception.
     * <p>
     * This method handles any exception that is not handled by any other exception handler.
     * It returns a ResponseEntity containing an ErrorResponseDto, which contains a message describing the error
     * The ResponseEntity has a status of INTERNAL_SERVER_ERROR.
     * </p>
     *
     * @param exception  the exception that was thrown
     * @param webRequest the request that caused the exception
     * @return ResponseEntity containing an ErrorResponseDto
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorTime(LocalDateTime.now());
        errorResponseDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom exception handler for ResourceNotFoundException.
     * <p>
     * This method handles a ResourceNotFoundException, which is thrown when a requested resource is not found.
     * It then returns a ResponseEntity containing an ErrorResponseDto, which contains a message describing the error.
     * The ResponseEntity has a status of NOT_FOUND.
     *
     * @param exception  the ResourceNotFoundException that was thrown
     * @param webRequest the request that caused the exception
     * @return ResponseEntity containing an ErrorResponseDto
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourseNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorTime(LocalDateTime.now());
        errorResponseDto.setErrorCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Custom exception handler for CustomerAlreadyExistsException.
     * <p>
     * This method handles a CustomerAlreadyExistsException, which is thrown when a customer already exists.
     * It then returns a ResponseEntity containing an ErrorResponseDto, which contains a message describing the error.
     * The ResponseEntity has a status of BAD_REQUEST.
     *
     * @param exception  the CustomerAlreadyExistsException that was thrown
     * @param webRequest the request that caused the exception
     * @return ResponseEntity containing an ErrorResponseDto
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourseNotFoundException(CustomerAlreadyExistsException exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorTime(LocalDateTime.now());
        errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
