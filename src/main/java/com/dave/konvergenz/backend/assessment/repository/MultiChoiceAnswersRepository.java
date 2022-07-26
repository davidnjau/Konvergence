package com.dave.konvergenz.backend.assessment.repository;

import com.dave.konvergenz.backend.assessment.entity.MultiChoiceAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiChoiceAnswersRepository extends JpaRepository<MultiChoiceAnswers, String> {
}