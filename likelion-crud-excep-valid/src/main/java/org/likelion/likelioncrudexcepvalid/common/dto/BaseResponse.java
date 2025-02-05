package org.likelion.likelioncrudexcepvalid.common.dto;

import lombok.*;
import org.likelion.likelioncrudexcepvalid.common.error.ErrorCode;
import org.likelion.likelioncrudexcepvalid.common.error.SuccessCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@Builder
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    // 전달할 data 없이 단순히 상태코드, 메시지만 전달하는 경우
    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    // 제네릭 메서드로 데이터를 포함하는 성공응답 객체 생성
    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    // ErrorCode 정보를 사용하여 오류 응답 객체 생성
    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    // ErrorCode 정보를 사용하되, 사용자 정의 메시지로 오류 응답 객체 생성
    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }
}