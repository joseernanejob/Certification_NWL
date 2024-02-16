package com.rocketseat.certification_nlw.modules.students.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.exceptions.AppException;
import com.rocketseat.certification_nlw.exceptions.BadRequestException;
import com.rocketseat.certification_nlw.exceptions.NotFoundException;
import com.rocketseat.certification_nlw.modules.questions.entities.AlternativeEntity;
import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_nlw.modules.students.datas.CreateAnswerDTO;
import com.rocketseat.certification_nlw.modules.students.datas.CreateCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.datas.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.AnswerEntity;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationEntity;
import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationRepository;

@Service
public class CertificationServices {

  @Autowired
  private CertificationRepository certificationRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private StudentServices studentServices;

  public List<CertificationEntity> verifyCertifications(VerifyHasCertificationDTO data) {
    List<CertificationEntity> certifications = certificationRepository.findByEmailAndTechnology(data.getEmail(),
        data.getTechnology());

    if (certifications.isEmpty())
      throw new NotFoundException("Certificado com a tecnologias " + data.getTechnology() + " não encontrado.");

    return certifications;
  }

  public CertificationEntity createCertification(CreateCertificationDTO data) {
    List<CertificationEntity> hasCertification = certificationRepository.findByEmailAndTechnology(data.getEmail(),
        data.getTechnology());

    if (!hasCertification.isEmpty())
      throw new AppException("Usuário já possui essa certificação", HttpStatus.BAD_REQUEST);

    if (data.getAnswers().isEmpty())
      throw new BadRequestException("Lista de respostas não enviada.");

    AtomicInteger correctAnswers = new AtomicInteger(0);
    List<AnswerEntity> alternatives = createAnswers(data.getAnswers(), correctAnswers);

    StudentEntity student = studentServices.verifyStudent(data.getEmail());
    Integer grade = (int) (100.0 / alternatives.size() * correctAnswers.doubleValue());
    CertificationEntity certification = CertificationEntity.builder()
        .technology(data.getTechnology())
        .grade(grade)
        .student(student)
        .build();

    CertificationEntity newCertification = certificationRepository.save(certification);

    alternatives.stream().forEach(element -> {
      element.setCertificationId(certification.getId());
    });
    certification.setAnswers(alternatives);
    System.out.println();
    certificationRepository.save(certification);

    return newCertification;

  }

  public List<AnswerEntity> createAnswers(List<CreateAnswerDTO> data, AtomicInteger grade) {
    List<AnswerEntity> alternatives = new ArrayList<>();

    data.stream().forEach(answer -> {
      QuestionEntity question = questionRepository.findById(answer.getQuestionId())
          .orElseThrow(() -> new NotFoundException("Questão com o id " + answer.getQuestionId() + " não encontrada."));

      AlternativeEntity correctAlternative = question.getAlternatives().stream()
          .filter(element -> element.getIsCorrect()).findFirst().get();

      if (answer.getAlternativeId().equals(correctAlternative.getId())) {
        answer.setIsCorrect(true);
        grade.incrementAndGet();
      } else {
        answer.setIsCorrect(false);
      }
      AnswerEntity newAnswer = AnswerEntity.builder()
          .answerId(answer.getAlternativeId())
          .correctAlternativeId(correctAlternative.getId())
          .questionId(question.getId())
          .isCorrect(answer.getIsCorrect())
          .build();

      alternatives.add(newAnswer);
    });
    return alternatives;
  }

}
