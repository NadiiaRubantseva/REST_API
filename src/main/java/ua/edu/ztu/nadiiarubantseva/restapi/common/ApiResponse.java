package ua.edu.ztu.nadiiarubantseva.restapi.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T, E extends Enum<E>> {
    private boolean status;
    private E errorCode;
    private T data;
    private long timestamp;

    public static <T, E extends Enum<E>> ApiResponse<T, E> success(T data) {
        return ApiResponse.<T, E>builder()
                .status(true)
                .errorCode(null)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T, E extends Enum<E>> ApiResponse<T, E> failure(E errorCode) {
        return ApiResponse.<T, E>builder()
                .status(false)
                .errorCode(errorCode)
                .data(null)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
