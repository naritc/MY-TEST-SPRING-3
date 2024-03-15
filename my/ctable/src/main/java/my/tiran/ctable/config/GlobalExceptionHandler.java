package my.tiran.ctable.config;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import my.tiran.ctable.common.constant.ResponseStatus;
import my.tiran.ctable.common.exception.BuException;
import my.tiran.ctable.model.dto.DefaultError;
import my.tiran.ctable.model.dto.ValidateError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ValidateError> validList = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ValidateError valid = new ValidateError();
            valid.setField(fieldError.getField());
            valid.setMessage(fieldError.getDefaultMessage());
            validList.add(valid);
        }

        DefaultError resp = new DefaultError();
        resp.setMessage(ex.getMessage());
        resp.errors(validList);

        return ResponseEntity.status(ResponseStatus.VALID.getHttpStatus()).body(resp);
    }

    @ExceptionHandler(BuException.class)
    public ResponseEntity<DefaultError> handleBuException(BuException buException, WebRequest request) {
        buException.printStackTrace();
        DefaultError resp = new DefaultError();
        resp.setMessage(buException.getCode().getMessage());
        return ResponseEntity.status(ResponseStatus.HANDLE_ERROR.getHttpStatus()).body(resp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnException(Exception exception, WebRequest request) {
        exception.printStackTrace();
        DefaultError resp = new DefaultError();
        resp.setMessage(exception.getMessage());
        return ResponseEntity.status(ResponseStatus.ERROR.getHttpStatus()).body(resp);
    }
}
