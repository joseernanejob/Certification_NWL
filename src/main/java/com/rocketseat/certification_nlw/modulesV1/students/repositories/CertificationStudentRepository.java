package com.rocketseat.certification_nlw.modulesV1.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rocketseat.certification_nlw.modulesV1.students.entities.CertificationsStudentEntity;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationsStudentEntity, UUID> {

  @Query("SELECT c FROM certifications c INNER JOIN c.student std WHERE std.email = :email AND c.technology = :technology")
  List<CertificationsStudentEntity> findByEmailAndTechnology(String email, String technology);
}
