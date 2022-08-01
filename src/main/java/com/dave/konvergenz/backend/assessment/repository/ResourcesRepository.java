package com.dave.konvergenz.backend.assessment.repository;

import com.dave.konvergenz.backend.assessment.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, String> {
}