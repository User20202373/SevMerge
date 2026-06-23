package com.example.SevMerge.notification;

import lombok.Data;
import com.example.SevMerge.core.util.MyDateUtil;
import java.sql.Timestamp;

public class NotificationResponse {

    @Data
    public static class ListDTO {
        private Long id;
        private String typeLabel; // 카드 제목
        private String content;
        private String url;  // 관련 페이지 이동 경로
        private boolean isRead;
        private String createdAt;

        public ListDTO(Notification notification) {
            this.id = notification.getId();
            this.typeLabel = notification.getType().getLabel();
            this.content = notification.getContent();
            this.url = notification.getUrl();
            this.isRead = notification.isRead();
            this.createdAt = MyDateUtil.timestampFormat(notification.getCreatedAt());
        }
    }
}
