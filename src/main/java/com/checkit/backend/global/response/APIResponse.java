package com.checkit.backend.global.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class APIResponse<T> {
    private final boolean success;
    private final String message;
    private final T error;
    private final T data;

    public static <T> APIResponse<T> success(T data) {
        return APIResponse.<T>builder()
                .success(true)
                .message("성공적으로 응답되었습니다")
                .error(null)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> fail(String message) {
        return APIResponse.<T>builder()
                .success(false)
                .message(message)
                .error(null)
                .data(null)
                .build();
    }

    public static <T> APIResponse<T> fail(T error, String message) {
        return APIResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .data(null)
                .build();
    }
}