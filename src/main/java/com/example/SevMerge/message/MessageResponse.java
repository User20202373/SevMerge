package com.example.SevMerge.message;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

public class MessageResponse {

    // 쪽지 목록
    @Data
    public static class ListDTO {
        private Long id;
        private String senderName;
        private String receiverName;
        private String title;
        private Boolean isRead;
        private Timestamp createdAt;

        public ListDTO(Message message) {
            this.id = message.getId();
            this.senderName = message.getSender().getName();
            this.receiverName = message.getReceiver().getName();
            this.title = message.getTitle();
            this.isRead = message.getIsRead();
            this.createdAt = message.getCreatedAt();
        }
    }

    // 쪽지 상세
    @Data
    public static class DetailDTO {
        private Long id;
        private Long senderId;
        private String senderName;
        private String receiverName;
        private Long projectId;
        private String projectTitle;  // project nullable이니 String으로
        private String title;
        private String content;
        private Boolean isRead;
        private Timestamp createdAt;
        public DetailDTO(Message message) {
            this.id = message.getId();
            this.senderId = message.getSender().getId();
            this.senderName = message.getSender().getName();
            this.receiverName = message.getReceiver().getName();
            this.projectId = message.getProject() != null ? message.getProject().getId() : null;
            this.projectTitle = message.getProject() != null ? message.getProject().getTitle() : null;
            this.title = message.getTitle();
            this.content = message.getContent();
            this.isRead = message.getIsRead();
            this.createdAt = message.getCreatedAt();
        }
    }

}
