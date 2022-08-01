package com.dave.konvergenz.backend.assessment.service_class.impl;

import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.assessment.entity.Questions;
import com.dave.konvergenz.backend.assessment.entity.Topics;
import com.dave.konvergenz.backend.assessment.repository.QuestionsRepository;
import com.dave.konvergenz.backend.assessment.service_class.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private TopicsServiceImpl topicsService;


    @Override
    public Results getQuestionDetails(String questionName) {
        return getQuestionDetailsData(questionName);
    }

    @Override
    public Results addQuestionToTopic(Questions question, String topicId) {
        return addQuestionToTopicData(question, topicId);
    }

    @Override
    public Results getAllTopicQuestions(String topicName) {
        return listQuestionsUnderTopic(topicName);
    }


    @Override
    public Results getQuestion(String topicId) {
        return getQuestionIdDetails(topicId);
    }

    private Results getQuestionIdDetails(String id){
        Optional<Questions> optionalQuestions = questionsRepository.findById(id);
        Questions question = optionalQuestions.orElse(null);
        if (question == null) {
            return new Results(400, "Question not found");
        } else {
            return new Results(200, question);
        }

    }

    private Results getQuestionDetailsData(String questionName) {

        Questions questions = questionsRepository.findByTitle(questionName);
        if (questions == null) {
            return new Results(400, "Question not found");
        }else {
            return new Results(200, questions);
        }

    }

    private Results addQuestionToTopicData(Questions question, String topicId) {

        Topics topics = topicsService.getTopicIdDetails(topicId);
        if (topics == null) {
            return new Results(400, "Topic not found");}
        else {
            question.setTopic(topics);
            questionsRepository.save(question);
            return new Results(200, "Question added successfully");
        }


    }

    private Results listQuestionsUnderTopic(String topicName) {

        Results topicsResults = topicsService.getTopicDetails(topicName);
        int code = topicsResults.getStatusCode();
        if (code == 200){
            Topics topics = (Topics) topicsResults.getDetails();
            Set<Questions> questions = topics.getQuestionsSet();
            return new Results(200, questions);
        }else {
            return new Results(400, "Topic details not found");
        }

    }

}

