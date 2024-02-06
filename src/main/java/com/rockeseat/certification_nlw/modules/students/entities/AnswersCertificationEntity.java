package com.rockeseat.certification_nlw.modules.students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersCertificationEntity {

  private UUID certificationId;
  private UUID studentId;
  private UUID questionId;
  private UUID answerId;
  private Boolean isCorret;

}
