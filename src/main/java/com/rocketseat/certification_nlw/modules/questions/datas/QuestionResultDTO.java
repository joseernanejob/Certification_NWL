package com.rocketseat.certification_nlw.modules.questions.datas;

import java.util.UUID;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResultDTO {
  private UUID id;
  private String technology;
  private String description;

  private List<AlternativesResultDTO> alternatives;
}
