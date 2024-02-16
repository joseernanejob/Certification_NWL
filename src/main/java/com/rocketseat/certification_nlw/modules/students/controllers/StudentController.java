package com.rocketseat.certification_nlw.modules.students.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modules.students.datas.CreateCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.datas.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationEntity;
import com.rocketseat.certification_nlw.modules.students.services.CertificationServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private CertificationServices certificationServices;

  @PostMapping("/certification/verify")
  public ResponseEntity<List<CertificationEntity>> verifyCertification(@RequestBody VerifyHasCertificationDTO data) {
    List<CertificationEntity> certification = certificationServices.verifyCertifications(data);
    return new ResponseEntity<>(certification, HttpStatus.OK);
  }

  @PostMapping("/certification")
  public ResponseEntity<CertificationEntity> createCertification(@RequestBody CreateCertificationDTO data) {
    CertificationEntity certification = certificationServices.createCertification(data);

    return new ResponseEntity<CertificationEntity>(certification, HttpStatus.CREATED);
  }

}
