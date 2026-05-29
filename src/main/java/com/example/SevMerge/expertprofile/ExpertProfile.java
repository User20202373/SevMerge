package com.example.SevMerge.expertprofile;


import com.example.SevMerge.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.math.BigDecimal;

@Entity
@Table(name = "expertprofile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpertProfile {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @JoinColumn(name = "client_id", nullable = false)
  private Member client;

  @Column(nullable = false, length = 255)
  private String profileImage;

  @Column(nullable = true)
  private Text intro;

  @Column(nullable = true)
  private Text career;

  @Column(nullable = true)
  private String speciality;

  @Column(precision = 3, scale = 2)
  private BigDecimal avrRating;

  @Column(nullable = false)
  private int totalReviews;

  @Column(nullable = false)
  private boolean isCertified;


}
