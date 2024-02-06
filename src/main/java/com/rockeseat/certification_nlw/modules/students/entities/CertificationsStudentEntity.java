package com.rockeseat.certification_nlw.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationsStudentEntity {

  private UUID id;
  private UUID studentId;
  private String technologie;
  private Double grate;
  private List<AnswersCertificationEntity> answersCertification;

}
