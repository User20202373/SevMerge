package com.example.SevMerge.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String handleCustomException(CustomException e, HttpServletRequest req) {
        log.warn("=== CustomException 발생 ===");
        log.warn("요청 URL : {}", req.getRequestURL());
        log.warn("에러 코드 : {}", e.getErrorCode());
        log.warn("에러 메시지 : {}", e.getMessage());

        String message = e.getMessage().replace("'", "\\'");

        // 401 - 로그인 페이지
        if (e.getErrorCode().getStatus().value() == 401) {
            return """
                    <script>
                    alert('%s');
                    location.href='/login-form';
                    </script>
                    """.formatted(message);
        }

        // 403, 400 - 이전 페이지 이동
        return """
                <script>
                alert('%s');
                history.back();
                </script>
                """.formatted(message);
    }

    // DB 제약조건 위반 처리
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e,
                                                        HttpServletRequest request) {
        log.warn("=== DB 제약조건 위반 ===");
        log.warn("요청 URL : {}", request.getRequestURL());
        log.warn("에러 메시지 : {}", e.getMessage());

        request.setAttribute("msg", "데이터 처리 중 오류가 발생했습니다.");
        return "err/500";
    }

    // 예상치 못한 RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.warn("=== 예상치 못한 RuntimeException 발생 ===");
        log.warn("요청 URL : {}", request.getRequestURL());
        log.warn("에러 메시지 : {}", e.getMessage());

        request.setAttribute("msg", "시스템 오류가 발생했습니다. 관리자에게 문의해주세요.");
        return "err/500";
    }
}
