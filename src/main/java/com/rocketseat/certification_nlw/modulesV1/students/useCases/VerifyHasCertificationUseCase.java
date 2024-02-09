package com.rocketseat.certification_nlw.modulesV1.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.modulesV1.students.dtos.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modulesV1.students.entities.CertificationsStudentEntity;
import com.rocketseat.certification_nlw.modulesV1.students.repositories.CertificationStudentRepository;

@Service
public class VerifyHasCertificationUseCase {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public Boolean execute(VerifyHasCertificationDTO data) {
    List<CertificationsStudentEntity> certification = certificationStudentRepository
        .findByEmailAndTechnology(data.getEmail(), data.getTech());

    if (!certification.isEmpty()) {
      return true;
    }

    return false;
  }
}
