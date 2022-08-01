package com.dave.konvergenz.backend.assessment.service_class.service;

import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.assessment.entity.Questions;

public interface QuestionsService {

    Results getQuestionDetails(String questionName);
    Results addQuestionToTopic(Questions question, String topicId);
    Results getAllTopicQuestions(String topicName);
    Results getQuestion(String topicName);

}
