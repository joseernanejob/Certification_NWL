package com.rocketseat.certification_nlw.modules.questions.datas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDTO {

  private String description;
  private String technology;
  private List<CreateAlternativeDTO> alternatives;
}
