package com.rockeseat.certification_nlw.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.rockeseat.certification_nlw.modules.students.dtos.VerifyHasCertificationDTO;

@Service
public class VerifyHasCertificationUseCase {

  public Boolean execute(VerifyHasCertificationDTO data) {
    if (data.getEmail().equals("test@gmail.com") && data.getTech().equals("Java"))
      return true;

    return false;
  }
}
