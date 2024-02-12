package com.rocketseat.certification_nlw.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rocketseat.certification_nlw.modules.students.entities.CertificationEntity;

public interface CertificationRepository extends JpaRepository<CertificationEntity, UUID> {

  @Query("SELECT c FROM certifications c INNER JOIN c.student std WHERE std.email = :email AND c.technology = :technology")
  public Optional<CertificationEntity> findByEmailAndTechnology(String email, String technology);
}
