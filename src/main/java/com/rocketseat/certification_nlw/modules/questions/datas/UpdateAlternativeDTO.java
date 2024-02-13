package com.rocketseat.certification_nlw.modules.questions.datas;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAlternativeDTO {

  private UUID id;
  private UUID questionId;
  private String description;
  private Boolean isCorrect;
}
