package com.dave.konvergenz.backend.assessment.service_class.service;

import com.dave.konvergenz.backend.assessment.entity.Topics;

import java.util.List;

public interface TopicsService {

    boolean isTopicExists(String topicName);
    Topics addTopic(Topics topic);
    Topics getTopicDetails(String topicName);
    void addTopicToUser(Long topicId, String userId);
    List<Topics> getAllTopics();

}
