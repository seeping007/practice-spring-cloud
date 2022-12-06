package com.csp.spring.web.handler;

import com.csp.spring.web.exception.BusinessException;
import com.csp.spring.web.exception.RawBusinessException;
import com.csp.spring.web.model.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ValidationException;
import java.util.ArrayList;

/**
 * Global exception handler
 *
 * @author chensiping
 * @since 2019-08-21
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final GlobalExceptionTranslator translator;

    public GlobalExceptionHandler(GlobalExceptionTranslator translator) {
        this.translator = translator;
    }

    private void logException(String message, Exception e, boolean isError) {
        if (log.isDebugEnabled()) {
            log.debug("{}", message, e);
        } else if (isError) {
            log.error("{}: {}", message, e.getMessage());
        } else {
            log.warn("{}: {}", message, e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public Object error(Exception e) {
        log.error("Internal Server Error: ", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(translator.translate(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Internal Server Error"));
    }

    /**
     * Business exception handler
     *
     * @param e business exception
     * @return error response
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> error(BusinessException e) {
        logException("BusinessException", e, false);
        if (e instanceof RawBusinessException rawBusinessException) {
            String contentType = rawBusinessException.getContentType();
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity
                    .status(e.getHttpCode());
            if (!ObjectUtils.isEmpty(contentType)) {
                bodyBuilder.contentType(MediaType.parseMediaType(contentType));
            }
            if (!ObjectUtils.isEmpty(rawBusinessException.getResponseHeaders())) {
                HttpHeaders headers = new HttpHeaders();
                rawBusinessException.getResponseHeaders().forEach((k, v) -> {
                    headers.put(k, new ArrayList<>(v));
                });
                bodyBuilder.headers(headers);
            }
            return bodyBuilder.body(e.getMessage());
        }
        return ResponseEntity
                .status(e.getHttpCode())
                .body(translator.translate(e));
    }

    //----------------------------- Following: common http exception ---------------------------------------------------
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> error(BindException e) {
        logException("BindException", e, false);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null
                ? String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage())
                : CommonConstant.EXCEPTION_MSG_INVALID_PARAMETER;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(this.translator.translate(HttpStatus.BAD_REQUEST.name(), message));
    }

    @ExceptionHandler(ValidationException.class)
    @ConditionalOnClass(value = {ValidationException.class})
    public ResponseEntity<Object> error(ValidationException e) {
        logException("ValidationException", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), CommonConstant.EXCEPTION_MSG_INVALID_PARAMETER));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> error(HttpMessageNotReadableException e) {
        logException("Message not readable", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), CommonConstant.EXCEPTION_MSG_JSON_PARSE_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> error(MethodArgumentNotValidException e) {
        logException("MethodArgumentNotValidException", e, false);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null
                ? String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage())
                : e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(this.translator.translate(HttpStatus.BAD_REQUEST.name(), message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> error(MethodArgumentTypeMismatchException e) {
        logException("MethodArgumentTypeMismatchException", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), CommonConstant.EXCEPTION_MSG_INVALID_PARAMETER));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> error(MissingServletRequestParameterException e) {
        logException("MissingServletRequestParameterException", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), String.format("%s is required", e.getParameterName())));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> error(NoHandlerFoundException e) {
        logException("NoHandlerFoundException", e, false);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(translator.translate(HttpStatus.NOT_FOUND.name(), CommonConstant.EXCEPTION_MSG_NO_HANDLER_FOUND));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> error(HttpRequestMethodNotSupportedException e) {
        logException("HttpRequestMethodNotSupportedException", e, false);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .body(translator.translate(HttpStatus.METHOD_NOT_ALLOWED.name(), CommonConstant.EXCEPTION_MSG_NO_HANDLER_FOUND));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> error(HttpMediaTypeNotSupportedException e) {
        logException("HttpMediaTypeNotSupportedException", e, false);
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .body(translator.translate(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name(), CommonConstant.EXCEPTION_MSG_MEDIA_TYPE_NOT_SUPPORTED));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> error(MaxUploadSizeExceededException e) {
        logException("MaxUploadSizeExceededException", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), CommonConstant.EXCEPTION_MSG_MAX_SIZE_EXCEEDED));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> error(MultipartException e) {
        logException("MultipartException", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), CommonConstant.EXCEPTION_MSG_INVALID_PARAMETER));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> error(MissingServletRequestPartException e) {
        logException("MissingServletRequestPartException", e, false);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(translator.translate(HttpStatus.BAD_REQUEST.name(), String.format("%s is required", e.getRequestPartName())));
    }

    @ExceptionHandler(ClientAbortException.class)
    public void error(ClientAbortException e) {
        logException("ClientAbortException", e, false);
    }
}
