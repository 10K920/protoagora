package com.protoagora.choosesideservice.repository;

import com.protoagora.choosesideservice.model.ChooseSide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChooseSideRepository extends JpaRepository<ChooseSide, Long> {
    ChooseSide findByTopicIdAndUserId(Long topicId, Long userId);
    List<ChooseSide> findAllByUserId(Long userId);
    List<ChooseSide> findAllByTopicId(Long topicId);
}
