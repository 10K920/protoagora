package com.protoagora.topicservice.repository;

import com.protoagora.topicservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
