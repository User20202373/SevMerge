package com.example.SevMerge.message;

import com.example.SevMerge.member.Member;
import com.example.SevMerge.project.Project;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Table(name="message_tb")
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("false")
    private Boolean isRead = false;

    @ColumnDefault("false")
    private Boolean isDeletedBySender = false;

    @ColumnDefault("false")
    private Boolean isDeletedByReceiver = false;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Message(Member sender, Member receiver, Project project,
                   String title, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.project = project;
        this.title = title;
        this.content = content;
        this.isRead = false;
        this.isDeletedBySender = false;
        this.isDeletedByReceiver = false;
    }

    public void read()             { this.isRead = true; }
    public void deleteBySender()   { this.isDeletedBySender = true; }
    public void deleteByReceiver() { this.isDeletedByReceiver = true; }
}
