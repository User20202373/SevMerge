package com.example.SevMerge.chatRoom;

import lombok.Data;


public class ChatRoomRequest {
    @Data
    public static class ChatRoomDTO {
        private Long projectId;

        private Long clientId;

        private String expertId;
    }

}
