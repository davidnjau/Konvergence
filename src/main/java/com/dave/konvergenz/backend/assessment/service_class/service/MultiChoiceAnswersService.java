package com.dave.konvergenz.backend.assessment.service_class.service;

import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.assessment.entity.MultiChoiceAnswers;

public interface MultiChoiceAnswersService {

    Results addMultiChoiceAnswer(String questionId, MultiChoiceAnswers answer);
    Results getMultiChoiceAnswerByQuestion(String questionId);
    Results getMultiChoiceDetails(String answerId);


}
