package com.rocketseat.certification_nlw.modules.students.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.exceptions.AppException;
import com.rocketseat.certification_nlw.exceptions.NotFoundException;
import com.rocketseat.certification_nlw.modules.students.datas.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationRepository;

@Service
public class CertificationServices {

  @Autowired
  private CertificationRepository certificationRepository;

  public CertificationEntity verifyCertifications(VerifyHasCertificationDTO data) {
    CertificationEntity certification = certificationRepository.findByEmailAndTechnology(data.getEmail(),
        data.getTechnology())
        .orElseThrow(() -> new NotFoundException("Usuário não possui a certificação " + data.getTechnology()));

    return certification;
  }

}
