package com.dave.konvergenz.backend.assessment.service_class.impl;

import com.dave.konvergenz.backend.assessment.repository.QuestionsRepository;
import com.dave.konvergenz.backend.assessment.service_class.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

}

