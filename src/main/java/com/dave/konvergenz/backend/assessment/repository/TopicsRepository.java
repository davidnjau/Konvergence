package com.dave.konvergenz.backend.assessment.repository;

import com.dave.konvergenz.backend.assessment.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topics, String> {
}