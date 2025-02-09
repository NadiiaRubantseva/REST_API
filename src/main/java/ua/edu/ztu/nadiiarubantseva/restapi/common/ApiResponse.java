package ua.edu.ztu.nadiiarubantseva.restapi.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T, E extends ErrorCode> {
    private boolean status;
    private E errorCode;
    private T data;
    private OffsetDateTime timestamp;

    public static <T> ApiResponse<T, CommonErrorCode> success(T data) {
        return ApiResponse.<T, CommonErrorCode>builder()
                .status(true)
                .errorCode(CommonErrorCode.OK)
                .data(data)
                .timestamp(OffsetDateTime.now(UTC))
                .build();
    }

    public static <T, E extends ErrorCode> ApiResponse<T, E> failure(E errorCode) {
        return ApiResponse.<T, E>builder()
                .status(false)
                .errorCode(errorCode)
                .data(null)
                .timestamp(OffsetDateTime.now(UTC))
                .build();
    }
}
