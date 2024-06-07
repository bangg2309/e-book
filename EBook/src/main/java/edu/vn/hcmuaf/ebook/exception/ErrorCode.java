package edu.vn.hcmuaf.ebook.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email existed", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH(1004, "Password not match", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1005, "Role not existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006, "User not existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "Unauthorized", HttpStatus.FORBIDDEN),
    EMAIL_NOT_EXISTED(1009, "Email not existed", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_EXISTED(1010, "Token not existed", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(1011, "Token expired", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_MATCH(1012, "Email not match", HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
