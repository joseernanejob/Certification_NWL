package com.rocketseat.certification_nlw.modulesV1.students.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyHasCertificationDTO {
  private String email;
  private String tech;

}
