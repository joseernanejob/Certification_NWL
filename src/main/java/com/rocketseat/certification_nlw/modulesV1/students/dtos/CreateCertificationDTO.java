package com.rocketseat.certification_nlw.modulesV1.students.dtos;

import java.util.List;

import lombok.Data;

@Data
public class CreateCertificationDTO {

  private String email;
  private String technology;
  private List<AnswersCertificationDTO> answers;

}
