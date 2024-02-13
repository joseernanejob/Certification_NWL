package com.rocketseat.certification_nlw.modules.questions.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.exceptions.NotFoundException;
import com.rocketseat.certification_nlw.mapper.Mapper;
import com.rocketseat.certification_nlw.modules.questions.datas.CreateQuestionDTO;
import com.rocketseat.certification_nlw.modules.questions.datas.QuestionResultDTO;
import com.rocketseat.certification_nlw.modules.questions.datas.UpdateQuestionDTO;
import com.rocketseat.certification_nlw.modules.questions.entities.AlternativeEntity;
import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.AlternativeRepository;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;

@Service
public class QuestionServices {

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AlternativeRepository alternativeRepository;

  public QuestionEntity create(CreateQuestionDTO data) {
    data.setTechnology(data.getTechnology().toUpperCase());
    QuestionEntity question = questionRepository.save(Mapper.parseObject(data, QuestionEntity.class));
    ;

    List<AlternativeEntity> alternatives = new ArrayList<>();
    question.getAlternatives().stream().forEach(alternative -> {
      AlternativeEntity newAlternative = Mapper.parseObject(alternative, AlternativeEntity.class);
      alternatives.add(alternativeRepository.save(newAlternative));
    });
    question.setAlternatives(alternatives);

    return question;

  }

  public QuestionResultDTO update(UpdateQuestionDTO data) {
    QuestionEntity question = this.findById(data.getId());
    question.update(data);
    questionRepository.save(question);

    return Mapper.parseObject(question, QuestionResultDTO.class);

  }

  public List<QuestionResultDTO> findAllByTechnology(String technology) {
    List<QuestionEntity> questions = questionRepository.findByTechnology(technology);
    if (questions.isEmpty())
      throw new NotFoundException("Não existe questões para a tecnologia " + technology);

    return Mapper.parseListObjects(questions, QuestionResultDTO.class);
  }

  public void delete(UUID id) {
    questionRepository.delete(this.findById(id));
  }

  public QuestionEntity findById(UUID id) {
    return questionRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Questão com o id " + id + " não encontrada."));

  }

}
