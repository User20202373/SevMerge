package com.example.SevMerge.message;

import com.example.SevMerge.core.exception.NotFoundException;
import com.example.SevMerge.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    // 쪽지함 리스트 페이징 처리 조회
    public Page<MessageResponse.ListDTO> findMessages(Member member, String box, int page) {
        Pageable pageable = PageRequest.of(Math.max(0, page - 1), 10, Sort.by("createdAt").descending());

        Page<Message> messagePage = box.equals("sent")
                ? messageRepository.findAllSentMessagesByPages(member, pageable)
                : messageRepository.findAllReceivedMessagesByPages(member, pageable);

        return messagePage.map(MessageResponse.ListDTO::new);
    }

    // 메세지 상세 조회
    @Transactional
    public MessageResponse.DetailDTO findMessageByIdWithDetails(@Param("id") Long id) {
        Message findMessage = messageRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new NotFoundException("쪽지를 찾을 수 없습니다."));
        return new MessageResponse.DetailDTO(findMessage);
    }
}
