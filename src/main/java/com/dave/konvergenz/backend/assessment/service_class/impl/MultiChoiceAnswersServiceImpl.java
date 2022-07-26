package com.dave.konvergenz.backend.assessment.service_class.impl;

import com.dave.konvergenz.backend.assessment.repository.MultiChoiceAnswersRepository;
import com.dave.konvergenz.backend.assessment.service_class.service.MultiChoiceAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiChoiceAnswersServiceImpl implements MultiChoiceAnswersService {

    @Autowired
    private MultiChoiceAnswersRepository multiChoiceAnswersRepository;

}
