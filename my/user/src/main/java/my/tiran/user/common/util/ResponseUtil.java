package my.tiran.user.common.util;

import my.tiran.user.common.constant.ResponseStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<T> success(String obj) {
        return success(obj);
    }

    public static <T> ResponseEntity<T> success(T obj) {
        return ResponseEntity.status(ResponseStatus.SUCCESS.getHttpStatus()).body(obj);
    }
}
