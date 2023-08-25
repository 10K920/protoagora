package com.protoagora.opinionservice.repository;

import com.protoagora.opinionservice.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findAllByUserId(Long userId);
    List<Opinion> findAllByTopicId(Long topicId);
    List<Opinion> findAllByTopicIdAndSide(Long topicId, Boolean side);
}
