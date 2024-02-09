package com.rocketseat.certification_nlw.modulesV1.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modulesV1.students.dtos.CreateCertificationDTO;
import com.rocketseat.certification_nlw.modulesV1.students.dtos.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modulesV1.students.entities.CertificationsStudentEntity;
import com.rocketseat.certification_nlw.modulesV1.students.useCases.CertificationStudentUseCase;
import com.rocketseat.certification_nlw.modulesV1.students.useCases.VerifyHasCertificationUseCase;

@RestController
@RequestMapping("/v1/students")
public class StutendController {

  @Autowired
  private VerifyHasCertificationUseCase verifyHasCertificationUseCase;

  @Autowired
  private CertificationStudentUseCase certificationStudentUseCase;

  @PostMapping("/verifyHasCertification")
  public ResponseEntity<String> verifyHasCertification(@RequestBody VerifyHasCertificationDTO data) {
    if (verifyHasCertificationUseCase.execute(data)) {
      return new ResponseEntity<>("Usuário jpa fez a prova.", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("Usuário pode fazer a prova", HttpStatus.OK);
  }

  @PostMapping("/answers")
  public ResponseEntity<CertificationsStudentEntity> createCertification(@RequestBody CreateCertificationDTO data)
      throws Exception {
    CertificationsStudentEntity certification = certificationStudentUseCase.execute(data);
    return new ResponseEntity<>(certification, HttpStatus.CREATED);
  }

}
