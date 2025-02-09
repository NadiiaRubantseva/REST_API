package ua.edu.ztu.nadiiarubantseva.restapi.security.auth.validator;

import ua.edu.ztu.nadiiarubantseva.restapi.common.ErrorCode;

public enum AuthErrorCode implements ErrorCode {
    USER_NOT_FOUND,
    INVALID_USER_DATA,
    INVALID_USER_EMAIL,
    INVALID_USER_PASSWORD
}
