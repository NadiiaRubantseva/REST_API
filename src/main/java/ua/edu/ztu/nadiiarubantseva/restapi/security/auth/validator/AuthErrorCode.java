package ua.edu.ztu.nadiiarubantseva.restapi.security;

import ua.edu.ztu.nadiiarubantseva.restapi.common.ErrorCode;

public enum AuthErrorCode implements ErrorCode {
    INVALID_CREDENTIALS,
    USER_NOT_FOUND,
    USER_ALREADY_EXISTS,
    INVALID_USER_DATA,
    INVALID_USER_EMAIL,
    INVALID_USER_PASSWORD
}
