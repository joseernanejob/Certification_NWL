package com.rocketseat.certification_nlw.modulesV1.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_nlw.modulesV1.students.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
  public Optional<StudentEntity> findByEmail(String email);

}
