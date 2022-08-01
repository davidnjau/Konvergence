package com.dave.konvergenz.backend.assessment.service_class.impl;

import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.assessment.entity.Topics;
import com.dave.konvergenz.backend.assessment.repository.TopicsRepository;
import com.dave.konvergenz.backend.assessment.service_class.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Override
    public Results addTopic(Topics topic) {
        return addTopiToDb(topic);
    }

    @Override
    public Results getTopicDetails(String topicName) {
        return getTopic(topicName);
    }



    @Override
    public Results getAllTopics() {
        return listAllTopics();
    }

    public Topics getTopicIdDetails(String id){
        Optional<Topics> topic = topicsRepository.findById(id);
        return topic.orElse(null);
    }

    private Results listAllTopics() {
        List<Topics> topics = topicsRepository.findAll();
        return new Results(200, topics);
    }

    private Results getTopic(String topicName) {

        Topics topics = topicsRepository.findByTopicName(topicName);
        if (topics == null) {
            return new Results(400, "Topic not found");
        }else {
            return new Results(200, topics);
        }


    }

    private Results addTopiToDb(Topics topic) {

        String topicName = topic.getTopicName();
        if (getTopic(topicName) == null) {

            Topics topics = topicsRepository.save(topic);
            if (topics != null) {
                return new Results(201, "Topic added successfully");
            }else {
                return new Results(400, "Topic not added");
            }
        } else {
            return new Results(400, "Topic already exists");
        }
    }

}

