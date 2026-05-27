package com.example.SevMerge.core.interceptor;

import com.example.SevMerge.core.exception.CustomException;
import com.example.SevMerge.core.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("sessionUser") == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        // 세션에서 유저 꺼내서 ADMIN 권한 확인
        Object sessionUser = session.getAttribute("sessionUser");
        // 추후 Member 엔티티 완성 후 role 체크 로직 추가 예정

        return true;
    }
}
