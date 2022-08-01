package com.dave.konvergenz.backend.assessment.service_class.service;

import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.assessment.entity.Topics;

import java.util.List;

public interface TopicsService {

    Results addTopic(Topics topic);
    Results getTopicDetails(String topicName);
    Results getAllTopics();

}
