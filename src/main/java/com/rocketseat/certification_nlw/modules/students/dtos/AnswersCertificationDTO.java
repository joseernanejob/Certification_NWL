package com.rocketseat.certification_nlw.modules.students.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AnswersCertificationDTO {
  private UUID questionId;
  private UUID alternativeId;
  private boolean isCorrect;

}
