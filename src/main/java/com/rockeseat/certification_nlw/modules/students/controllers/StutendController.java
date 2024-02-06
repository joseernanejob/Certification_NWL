package com.rockeseat.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockeseat.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;
import com.rockeseat.certification_nlw.modules.students.useCases.VerifyHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StutendController {

  @Autowired
  private VerifyHasCertificationUseCase verifyHasCertificationUseCase;

  @PostMapping("/verifyHasCertificationstu")
  public ResponseEntity<String> verifyHasCertification(@RequestBody VerifyHasCertificationDTO data) {
    if (verifyHasCertificationUseCase.execute(data)) {

      return new ResponseEntity<>("Usuário pode fazer a prova.", HttpStatus.OK);
    }
    return new ResponseEntity<>("Usuário não pode fazer a prova", HttpStatus.UNAUTHORIZED);
  }
}
