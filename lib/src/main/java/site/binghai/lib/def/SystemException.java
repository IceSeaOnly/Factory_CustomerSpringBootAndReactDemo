package site.binghai.lib.def;

import lombok.Data;
import site.binghai.lib.enums.ErrorCode;

/**
 * @author huaishuo
 * @date 2019/1/6 下午3:24
 **/
@Data
public class SystemException extends RuntimeException {
    private ErrorCode errorCode;

    public SystemException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SystemException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                           ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
