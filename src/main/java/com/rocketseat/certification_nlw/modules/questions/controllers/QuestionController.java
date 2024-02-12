package com.rocketseat.certification_nlw.modules.questions.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rocketseat.certification_nlw.modules.questions.datas.CreateQuestionDTO;
import com.rocketseat.certification_nlw.modules.questions.datas.QuestionResultDTO;
import com.rocketseat.certification_nlw.modules.questions.datas.UpdateQuestionDTO;
import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.services.QuestionServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionServices questionServices;

  @PostMapping
  public ResponseEntity<QuestionEntity> create(@RequestBody @Valid CreateQuestionDTO data) {
    QuestionEntity question = questionServices.create(data);
    return new ResponseEntity<QuestionEntity>(question, HttpStatus.CREATED);
  }

  @GetMapping("/{technology}")
  public ResponseEntity<List<QuestionResultDTO>> getAllQuestionsByTechnology(@PathVariable String technology) {
    List<QuestionResultDTO> questions = questionServices.findAllByTechnology(technology.toUpperCase());
    return new ResponseEntity<List<QuestionResultDTO>>(questions, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<QuestionResultDTO> update(@RequestBody UpdateQuestionDTO data) {
    QuestionResultDTO question = questionServices.update(data);
    return new ResponseEntity<QuestionResultDTO>(question, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    questionServices.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
