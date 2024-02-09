package com.rocketseat.certification_nlw.modulesV1.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.modulesV1.questions.entities.AlternativeEntity;
import com.rocketseat.certification_nlw.modulesV1.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modulesV1.questions.repositories.QuestionRepository;
import com.rocketseat.certification_nlw.modulesV1.students.dtos.CreateCertificationDTO;
import com.rocketseat.certification_nlw.modulesV1.students.dtos.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modulesV1.students.entities.AnswersCertificationEntity;
import com.rocketseat.certification_nlw.modulesV1.students.entities.CertificationsStudentEntity;
import com.rocketseat.certification_nlw.modulesV1.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modulesV1.students.repositories.CertificationStudentRepository;
import com.rocketseat.certification_nlw.modulesV1.students.repositories.StudentRepository;

@Service
public class CertificationStudentUseCase {
  @Autowired
  private QuestionRepository repository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CertificationStudentRepository certificationRepository;

  @Autowired
  private VerifyHasCertificationUseCase verifyHasCertificationUseCase;

  public CertificationsStudentEntity execute(CreateCertificationDTO data) throws Exception {
    boolean hasCertification = verifyHasCertificationUseCase
        .execute(new VerifyHasCertificationDTO(data.getEmail(), data.getTechnology()));

    if (hasCertification)
      throw new Exception("Você já tirou sua certificação");

    List<QuestionEntity> questionsEntity = repository.findByTechnology(data.getTechnology().toUpperCase());

    List<AnswersCertificationEntity> answersCertifications = new ArrayList<>();

    AtomicInteger correctAnswers = new AtomicInteger(0);

    data.getAnswers().stream()
        .forEach(questionAnswers -> {
          QuestionEntity question = questionsEntity.stream()
              .filter(q -> q.getId().equals(questionAnswers.getQuestionId())).findFirst().get();

          AlternativeEntity correctAnswer = question.getAlternatives().stream()
              .filter(ans -> ans.getIsCorrect()).findFirst().get();

          if (questionAnswers.getAlternativeId().equals(correctAnswer.getId())) {
            questionAnswers.setCorrect(true);
            correctAnswers.incrementAndGet();
          } else {
            questionAnswers.setCorrect(false);
          }
          AnswersCertificationEntity answer = AnswersCertificationEntity.builder()
              .answerId(questionAnswers.getAlternativeId())
              .questionId(questionAnswers.getQuestionId())
              .isCorrect(questionAnswers.isCorrect())
              .build();

          answersCertifications.add(answer);
        });

    Optional<StudentEntity> findStudent = studentRepository.findByEmail(data.getEmail());
    StudentEntity studentId;
    if (findStudent.isEmpty()) {
      StudentEntity newStudent = new StudentEntity();
      newStudent.setEmail(data.getEmail());
      studentId = studentRepository.save(newStudent);
    } else {
      studentId = findStudent.get();
    }

    CertificationsStudentEntity certification = CertificationsStudentEntity.builder()
        .technology(data.getTechnology())
        .student(studentId)
        .grate(correctAnswers.get())
        .build();

    CertificationsStudentEntity newCertification = certificationRepository.save(certification);

    answersCertifications.forEach(answerCertification -> {
      answerCertification.setCertificationId(certification.getId());
      answerCertification.setCertification(certification);
    });

    certification.setAnswersCertification(answersCertifications);
    certificationRepository.save(certification);

    return newCertification;

  }

}
