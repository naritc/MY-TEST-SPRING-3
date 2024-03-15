package my.tiran.ctable.common.util;

import my.tiran.ctable.common.constant.ResponseStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<T> success(String obj) {
        return success(obj);
    }

    public static <T> ResponseEntity<T> success(T obj) {
        return ResponseEntity.status(ResponseStatus.SUCCESS.getHttpStatus()).body(obj);
    }
}
