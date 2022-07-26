package com.dave.konvergenz.backend.assessment.service_class.impl;

import com.dave.konvergenz.backend.assessment.entity.Topics;
import com.dave.konvergenz.backend.assessment.repository.TopicsRepository;
import com.dave.konvergenz.backend.assessment.service_class.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Override
    public boolean isTopicExists(String topicName) {
        return false;
    }

    @Override
    public Topics addTopic(Topics topic) {
        return null;
    }

    @Override
    public Topics getTopicDetails(String topicName) {
        return null;
    }

    @Override
    public void addTopicToUser(Long topicId, String userId) {

    }

    @Override
    public List<Topics> getAllTopics() {
        return null;
    }
}

