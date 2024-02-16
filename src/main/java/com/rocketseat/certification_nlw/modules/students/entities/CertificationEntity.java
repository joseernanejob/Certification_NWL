package com.rocketseat.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "certifications")
@Builder
public class CertificationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  private StudentEntity student;

  @Column(length = 60, nullable = false)
  private String technology;

  @Column(nullable = false)
  private int grade;

  @OneToMany(cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<AnswerEntity> answers;

  @CreationTimestamp
  private LocalDateTime createAt;

}
