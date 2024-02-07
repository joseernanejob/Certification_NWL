package com.rocketseat.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.useCases.VerifyHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StutendController {

  @Autowired
  private VerifyHasCertificationUseCase verifyHasCertificationUseCase;

  @PostMapping("/verifyHasCertification")
  public ResponseEntity<String> verifyHasCertification(@RequestBody VerifyHasCertificationDTO data) {
    if (verifyHasCertificationUseCase.execute(data)) {
      return new ResponseEntity<>("Usuário jpa fez a prova.", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("Usuário pode fazer a prova", HttpStatus.OK);
  }
}
