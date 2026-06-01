package com.example.SevMerge.chatRoom;

import com.example.SevMerge.member.MemberRepository;
import com.example.SevMerge.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    public ChatRoomResponse createChatRoom(ChatRoomRequest reqDTO) {

    }
}
