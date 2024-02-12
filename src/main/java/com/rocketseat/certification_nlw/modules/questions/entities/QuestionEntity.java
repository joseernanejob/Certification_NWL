package com.rocketseat.certification_nlw.modules.questions.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.rocketseat.certification_nlw.modules.questions.datas.UpdateQuestionDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "questions")
public class QuestionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column
  private String description;

  @Column(length = 50)
  private String technology;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "question_id")
  private List<AlternativeEntity> alternatives;

  @CreationTimestamp
  private LocalDateTime createAt;

  public void update(UpdateQuestionDTO data) {
    this.description = data.getDescription();
    this.technology = data.getTechnology().toUpperCase();
  }
}
