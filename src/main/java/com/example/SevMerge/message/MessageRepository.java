package com.example.SevMerge.message;

import com.example.SevMerge.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {

    // 받은 메세지 조회
//    @Query("""
//           SELECT m FROM Message m JOIN FETCH m.sender WHERE m.receiver = :receiver AND m.isdeleted = false ORDER BY m.createdAt DESC
//           """)
//    List<Message> findReceivedMessages(@Param("receiver") Member receiver);

    @Query(value = "SELECT m FROM Message m JOIN FETCH m.sender JOIN FETCH m.receiver WHERE m.receiver = :receiver AND m.isDeletedByReceiver = false",
           countQuery = "SELECT COUNT(m) FROM Message m WHERE m.receiver = :receiver AND m.isDeletedByReceiver = false")
    Page<Message> findAllReceivedMessagesByPages(@Param("receiver") Member receiver, Pageable pageable);

    // 보낸 메세지 조회
//    @Query("""
//           SELECT m FROM Message m JOIN FETCH m.receiver WHERE m.sender = :sender AND m.isDeletedBySender = false ORDER BY m.createdAt DESC
//           """)
//    List<Message> findSentMessages(@Param("sender") Member sender);

    @Query(value = "SELECT m FROM Message m JOIN FETCH m.sender JOIN FETCH m.receiver WHERE m.sender = :sender AND m.isDeletedBySender = false",
            countQuery = "SELECT COUNT(m) FROM Message m WHERE m.sender = :sender AND m.isDeletedBySender = false")
    Page<Message> findAllSentMessagesByPages(@Param("sender") Member sender, Pageable pageable);

    // 쪽지 상세조회
    @Query("SELECT m FROM Message m JOIN FETCH m.sender JOIN FETCH m.receiver LEFT JOIN FETCH m.project WHERE m.id = :id")
    Optional<Message> findByIdWithDetails(@Param("id") Long id);

}
