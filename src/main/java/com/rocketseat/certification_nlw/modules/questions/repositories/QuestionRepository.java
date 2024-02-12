package com.rocketseat.certification_nlw.modules.questions.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

  public List<QuestionEntity> findAllByTechnology(String Technology);

  public Optional<QuestionEntity> findByTechnology(String technology);
}
