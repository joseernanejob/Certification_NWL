package com.rockeseat.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certification_students")
public class AnswersCertificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  private CertificationsStudentEntity certification;

  @ManyToOne
  private StudentEntity student;

  @Column(name = "question_id")
  private UUID questionId;

  @Column(name = "answer_id")
  private UUID answerId;

  @Column(name = "is_correct", nullable = false)
  private Boolean isCorrect;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
