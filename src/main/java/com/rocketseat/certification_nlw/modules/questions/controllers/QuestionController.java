package com.rocketseat.certification_nlw.modules.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.mapper.Mapper;
import com.rocketseat.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping("/technology/{technology}")
  public ResponseEntity<List<QuestionResultDTO>> getMethodName(@PathVariable String technology) {
    List<QuestionResultDTO> questions = Mapper.parseListObjects(
        questionRepository.findByTechnology(technology), QuestionResultDTO.class);

    return new ResponseEntity<>(questions, HttpStatus.OK);
  }

}
