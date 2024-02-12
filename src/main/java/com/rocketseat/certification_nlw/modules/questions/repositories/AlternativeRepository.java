package com.rocketseat.certification_nlw.modules.questions.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_nlw.modules.questions.entities.AlternativeEntity;

public interface AlternativeRepository extends JpaRepository<AlternativeEntity, UUID> {

}
