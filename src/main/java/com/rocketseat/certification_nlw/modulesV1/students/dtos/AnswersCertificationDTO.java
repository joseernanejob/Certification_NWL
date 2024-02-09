package com.rocketseat.certification_nlw.modulesV1.students.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AnswersCertificationDTO {
  private UUID questionId;
  private UUID alternativeId;
  private boolean isCorrect;

}
