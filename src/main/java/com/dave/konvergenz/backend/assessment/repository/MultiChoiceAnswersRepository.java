package com.dave.konvergenz.backend.assessment.repository;

import com.dave.konvergenz.backend.assessment.entity.MultiChoiceAnswers;
import com.dave.konvergenz.backend.assessment.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultiChoiceAnswersRepository extends JpaRepository<MultiChoiceAnswers, String> {
    List<MultiChoiceAnswers> findByQuestions(Questions questions);
}