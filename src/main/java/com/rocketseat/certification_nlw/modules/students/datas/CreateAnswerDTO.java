package com.rocketseat.certification_nlw.modules.students.datas;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateAnswerDTO {
  private UUID questionId;
  private UUID correctAlternativeId;
  private UUID alternativeId;
  private Boolean isCorrect;

}
