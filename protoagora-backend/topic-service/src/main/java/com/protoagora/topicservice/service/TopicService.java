package com.protoagora.topicservice.service;

import com.protoagora.topicservice.dto.TopicRequest;
import com.protoagora.topicservice.dto.TopicResponse;
import com.protoagora.topicservice.model.Topic;
import com.protoagora.topicservice.repository.TopicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;

    public void postTopic(TopicRequest topicRequest){
        Topic topic = new Topic();
        topic.setUserId(topicRequest.getUserId());
        topic.setTopicTitle(topicRequest.getTopicTitle());
        topic.setTopicDescription(topicRequest.getTopicDescription());
        topic.setTopicContent(topicRequest.getTopicContent());
        topic.setOption1(topicRequest.getOption1());
        topic.setOption1Count(0);
        topic.setOption2(topicRequest.getOption2());
        topic.setOption2Count(0);
        topic.setOpinionCount(0);

        topicRepository.save(topic);
        log.info("Topic {} is posted", topic.getId());
    }
    
    public boolean incrementOption1Count(Long topicId){
        Topic topic = topicRepository.findById(topicId).orElse(null);
        if (topic != null) {
            topic.setOption1Count(topic.getOption1Count() + 1);
            topicRepository.save(topic);
            return true;
        }
        return false;
    }

    public boolean incrementOption2Count(Long topicId){
        Topic topic = topicRepository.findById(topicId).orElse(null);
        if (topic != null) {
            topic.setOption2Count(topic.getOption2Count() + 1);
            topicRepository.save(topic);
            return true;
        }
        return false;
    }

    public boolean incrementOpinionCount(Long topicId){
        Topic topic = topicRepository.findById(topicId).orElse(null);
        if (topic != null) {
            topic.setOpinionCount(topic.getOpinionCount() + 1);
            topicRepository.save(topic);
            return true;
        }
        return false;
    }


    public List<TopicResponse> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        log.info("Here are the latest posts!");

        return topics.stream().map(this::mapToTopicResponse).toList();
    }

    public TopicResponse getThisTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElse(null);
        TopicResponse topicResponse = new TopicResponse();
        if (topic != null) {
            topicResponse.setId(topic.getId());
            topicResponse.setUserId(topic.getUserId());
            topicResponse.setPostTimestamp(topic.getPostTimestamp());
            topicResponse.setTopicTitle(topic.getTopicTitle());
            topicResponse.setTopicDescription(topic.getTopicDescription());
            topicResponse.setTopicContent(topic.getTopicContent());
            topicResponse.setOption1(topic.getOption1());
            topicResponse.setOption1Count(topic.getOption1Count());
            topicResponse.setOption2(topic.getOption2());
            topicResponse.setOption2Count(topic.getOption2Count());
            topicResponse.setOpinionCount(topic.getOpinionCount());
        }

        return topicResponse;
    }

    private TopicResponse mapToTopicResponse(Topic topic) {
        TopicResponse topicResponse = new TopicResponse();
        topicResponse.setId(topic.getId());
        topicResponse.setUserId(topic.getUserId());
        topicResponse.setPostTimestamp(topic.getPostTimestamp());
        topicResponse.setTopicTitle(topic.getTopicTitle());
        topicResponse.setTopicDescription(topic.getTopicDescription());
        topicResponse.setTopicContent(topic.getTopicContent());
        topicResponse.setOption1(topic.getOption1());
        topicResponse.setOption1Count(topic.getOption1Count());
        topicResponse.setOption2(topic.getOption2());
        topicResponse.setOption2Count(topic.getOption2Count());
        topicResponse.setOpinionCount(topic.getOpinionCount());

        return topicResponse;
    }
}
