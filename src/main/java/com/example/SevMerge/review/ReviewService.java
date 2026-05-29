package com.example.SevMerge.review;

import jakarta.servlet.http.HttpSession;

public class ReviewService {


    public void save(Review review) {

        // 로그인 인터셉터 처리

        //  DTO 빌더 리뷰는 일반 전문가 둘다 작성 가능하니 Member 하나만 씀
        // 전문가는 Member 를 가지고 있다
        ReviewRequest.SaveReviewDTO reviewDTO = ReviewRequest.SaveReviewDTO
                .builder()
                .member(review.getMember())
                .content(review.getContent())
                .totalStar(review.getTotalStar())
                .countStar(review.getCountStar())
                .build();
        // 유효성 검사



        // 트랜잭션

        // save 레파지토리


    }
}
