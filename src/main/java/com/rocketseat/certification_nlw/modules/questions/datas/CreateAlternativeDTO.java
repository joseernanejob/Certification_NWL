package com.rocketseat.certification_nlw.modules.questions.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlternativeDTO {

  private String description;
  private Boolean isCorrect;
}
