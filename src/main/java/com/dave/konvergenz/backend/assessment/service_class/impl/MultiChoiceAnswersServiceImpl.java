package com.dave.konvergenz.backend.assessment.service_class.impl;

import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.assessment.entity.MultiChoiceAnswers;
import com.dave.konvergenz.backend.assessment.entity.Questions;
import com.dave.konvergenz.backend.assessment.repository.MultiChoiceAnswersRepository;
import com.dave.konvergenz.backend.assessment.service_class.service.MultiChoiceAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultiChoiceAnswersServiceImpl implements MultiChoiceAnswersService {

    @Autowired
    private MultiChoiceAnswersRepository multiChoiceAnswersRepository;

    @Autowired
    private QuestionsServiceImpl questionsService;

    @Override
    public Results addMultiChoiceAnswer(String questionId, MultiChoiceAnswers answer) {

        Results results = questionsService.getQuestionDetails(questionId);
        int status = results.getStatusCode();
        if (status == 200){

            answer.setQuestions((Questions) results.getDetails());
            multiChoiceAnswersRepository.save(answer);
            return new Results(200, "Answer added successfully");
        }else {
            return new Results(400, "Answer not added");
        }

    }

    @Override
    public Results getMultiChoiceAnswerByQuestion(String questionId) {

        Results results = questionsService.getQuestionDetails(questionId);
        int status = results.getStatusCode();
        if (status == 200) {

            Questions question = (Questions) results.getDetails();
            List<MultiChoiceAnswers> answers = multiChoiceAnswersRepository.findByQuestions(question);
            return new Results(200, answers);
        } else {
            return new Results(400, "Answer not found");
        }

    }

    @Override
    public Results getMultiChoiceDetails(String answerId) {
        Optional<MultiChoiceAnswers> optionalAnswers = multiChoiceAnswersRepository.findById(answerId);
        MultiChoiceAnswers answer = optionalAnswers.orElse(null);
        if (answer == null) {
            return new Results(400, "Answer not found");
        } else {
            return new Results(200, answer);
        }
    }

}
