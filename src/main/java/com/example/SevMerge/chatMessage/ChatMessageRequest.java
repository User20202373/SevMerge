package com.example.SevMerge.chatMessage;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private Long roomId;
    private String text;
}
