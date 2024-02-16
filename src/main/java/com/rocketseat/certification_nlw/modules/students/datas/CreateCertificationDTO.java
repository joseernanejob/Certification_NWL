package com.rocketseat.certification_nlw.modules.students.datas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCertificationDTO {
  private String email;
  private String technology;
  private List<CreateAnswerDTO> answers;
}
