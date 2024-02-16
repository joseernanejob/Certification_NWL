package com.rocketseat.certification_nlw.modules.students.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentServices {

  @Autowired
  private StudentRepository studentRepository;

  public StudentEntity verifyStudent(String email) {
    Optional<StudentEntity> verifyStudent = studentRepository.findByEmail(email);
    StudentEntity student;
    if (verifyStudent.isEmpty()) {
      StudentEntity newStudent = new StudentEntity();
      newStudent.setEmail(email);
      student = studentRepository.save(newStudent);
    } else {
      student = verifyStudent.get();
    }

    return student;
  }
}
