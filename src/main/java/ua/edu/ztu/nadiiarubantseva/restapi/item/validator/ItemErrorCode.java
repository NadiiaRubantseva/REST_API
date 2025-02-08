package ua.edu.ztu.nadiiarubantseva.restapi.item;

import ua.edu.ztu.nadiiarubantseva.restapi.common.ErrorCode;

public enum ItemErrorCode implements ErrorCode {
    ITEM_NOT_FOUND,
    ITEM_ALREADY_EXISTS,
    INVALID_ITEM_DATA,
    INVALID_ITEM_NAME,
    INVALID_ITEM_DESCRIPTION
}
