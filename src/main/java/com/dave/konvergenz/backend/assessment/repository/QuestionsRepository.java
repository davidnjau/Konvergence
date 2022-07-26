package com.dave.konvergenz.backend.assessment.repository;

import com.dave.konvergenz.backend.assessment.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, String> {
}