package com.dave.konvergenz.backend.assessment.entity;

import com.dave.konvergenz.backend.authentication.entity.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
public class Topics {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uui_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true)
    private String topicName;

    private String topicDescription;

    @OneToMany(
            mappedBy = "topic",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Questions> questionsSet;

    @OneToMany(
            mappedBy = "topic",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Resources> resourcesSet;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public Topics() {

    }

    public Topics(String topicName, String topicDescription) {
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Questions> getQuestionsSet() {
        return questionsSet;
    }

    public void setQuestionsSet(Set<Questions> questionsSet) {
        this.questionsSet = questionsSet;
    }

    public Set<Resources> getResourcesSet() {
        return resourcesSet;
    }

    public void setResourcesSet(Set<Resources> resourcesSet) {
        this.resourcesSet = resourcesSet;
    }
}
