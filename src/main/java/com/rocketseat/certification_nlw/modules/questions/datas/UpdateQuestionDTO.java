package com.rocketseat.certification_nlw.modules.questions.datas;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionDTO {

  private UUID id;
  private String description;
  private String technology;

}
