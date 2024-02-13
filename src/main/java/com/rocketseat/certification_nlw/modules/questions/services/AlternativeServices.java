package com.rocketseat.certification_nlw.modules.questions.services;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.exceptions.AppException;
import com.rocketseat.certification_nlw.exceptions.NotFoundException;
import com.rocketseat.certification_nlw.modules.questions.datas.CreateAlternativeDTO;
import com.rocketseat.certification_nlw.modules.questions.datas.UpdateAlternativeDTO;
import com.rocketseat.certification_nlw.modules.questions.entities.AlternativeEntity;
import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.AlternativeRepository;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;

@Service
public class AlternativeServices {

  @Autowired
  private AlternativeRepository alternativeRepository;

  @Autowired
  private QuestionRepository questionRepository;

  public AlternativeEntity create(CreateAlternativeDTO data) {
    QuestionEntity question = questionRepository.findById(data.getQuestionId())
        .orElseThrow(() -> new NotFoundException("Questão com o id " + data.getQuestionId() + " não encontrada."));
    List<AlternativeEntity> alternatives = question.getAlternatives();
    if (alternatives.size() >= 4)
      throw new AppException("Questão pode possuir no máximo 4 alternativas", HttpStatus.UNAUTHORIZED);

    AlternativeEntity alternativeEntity = new AlternativeEntity();
    alternativeEntity.setDescription(data.getDescription());
    alternativeEntity.setIsCorrect(data.getIsCorrect());

    if (data.getIsCorrect()) {
      alternatives.stream().forEach(alternative -> {
        if (alternative.getIsCorrect()) {
          alternative.setIsCorrect(false);
          alternativeRepository.save(alternative);
        }
      });
    }

    alternatives.add(alternativeRepository.save(alternativeEntity));
    question.setAlternatives(alternatives);
    questionRepository.save(question);

    return alternativeEntity;
  }

  public AlternativeEntity update(UpdateAlternativeDTO data) {
    AlternativeEntity alternative = this.findById(data.getId());

    if (!data.getIsCorrect() && alternative.getIsCorrect())
      throw new AppException(
          "Impossível atualizar o campo 'isCorrect' para false, primeiro atualize o campo 'isCorrect' para true de outra alternativa.",
          HttpStatus.BAD_REQUEST);

    if (!alternative.getIsCorrect() && data.getIsCorrect()) {
      QuestionEntity question = questionRepository.findById(data.getQuestionId())
          .orElseThrow(() -> new NotFoundException("Questão com o id " + data.getQuestionId() + " não encontrada."));

      AlternativeEntity alternativeQuestion = question.getAlternatives().stream()
          .filter(element -> element.getId() == data.getId()).findFirst().get();

      if (alternativeQuestion == null)
        throw new AppException(
            "Questão com o id " + data.getQuestionId() + " não possui a alternativa com o id " + data.getId(),
            HttpStatus.BAD_REQUEST);

      question.getAlternatives().stream()
          .forEach(element -> {
            if (element.getIsCorrect() && element.getId() != alternative.getId()) {
              element.setIsCorrect(false);
              alternativeRepository.save(element);
            }
          });
    }

    alternative.setDescription(data.getDescription());
    alternative.setIsCorrect(data.getIsCorrect());
    alternativeRepository.save(alternative);

    return alternative;
  }

  public AlternativeEntity findById(UUID id) {
    return alternativeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(("Alternativa com id " + " não encontrada.")));
  }

  public void delete(UUID id) {
    AlternativeEntity alternativeEntity = this.findById(id);
    alternativeRepository.delete(alternativeEntity);
  }

}
