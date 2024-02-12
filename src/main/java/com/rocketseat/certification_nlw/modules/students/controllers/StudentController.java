package com.rocketseat.certification_nlw.modules.students.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modules.students.datas.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationEntity;
import com.rocketseat.certification_nlw.modules.students.services.CertificationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private CertificationServices certificationServices;

  @PostMapping("/certification/verify")
  public ResponseEntity<CertificationEntity> verifyCertification(@RequestBody VerifyHasCertificationDTO data) {
    CertificationEntity certification = certificationServices.verifyCertifications(data);
    return new ResponseEntity<CertificationEntity>(certification, HttpStatus.OK);
  }

}
