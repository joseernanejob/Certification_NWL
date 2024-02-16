package com.rocketseat.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers")
@Builder
public class AnswerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "certification_id")
  private UUID certificationId;

  @ManyToOne
  @JoinColumn(name = "certification_id", insertable = false, updatable = false)
  @JsonBackReference
  private CertificationEntity certification;

  @Column(name = "question_id", nullable = false)
  private UUID questionId;

  @Column(name = "answer_id", nullable = false)
  private UUID answerId;

  @Column(name = "correct_alternative_id", nullable = false)
  private UUID correctAlternativeId;

  @Column(name = "is_correct", nullable = false)
  private Boolean isCorrect;

  @CreationTimestamp
  private LocalDateTime createAt;

}
