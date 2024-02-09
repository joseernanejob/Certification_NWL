package com.rocketseat.certification_nlw.modulesV1.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_nlw.modulesV1.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

  List<QuestionEntity> findByTechnology(String technology);
}
