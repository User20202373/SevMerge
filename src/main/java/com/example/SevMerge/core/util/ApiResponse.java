package com.example.SevMerge.core.util;

import lombok.Data;

@Data
    // 제네릭 사용해서 형변환 간단하게 사용
public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;


    // 성공(데이터)
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "요청이 성공했습니다.", data);
    }

    // 성공(메세지만)
    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<>(true, message, null);
    }

    // 실패시
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }


}
