package com.rocketseat.certification_nlw.modules.questions.datas;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlternativeDTO {

  private UUID questionId;
  private String description;
  private Boolean isCorrect;
}
