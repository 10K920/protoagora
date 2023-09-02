package com.protoagora.topicservice.controller;

import com.protoagora.topicservice.dto.TopicRequest;
import com.protoagora.topicservice.dto.TopicResponse;
import com.protoagora.topicservice.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/topic")
@RequiredArgsConstructor
@CrossOrigin
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/post_topic")
    @ResponseStatus(HttpStatus.CREATED)
    public void postTopic(@RequestBody TopicRequest topicRequest) {
        topicService.postTopic(topicRequest);
    }

    @GetMapping("/incrementOption1")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean incrementOption1Count(@RequestParam("topicId") Long topicId) {
        return topicService.incrementOption1Count(topicId);
    }

    @GetMapping("/incrementOption2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean incrementOption2Count(@RequestParam("topicId") Long topicId) {
        return topicService.incrementOption2Count(topicId);
    }

    @GetMapping("/get_all_topics")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicResponse> getAllTopics(){
        return topicService.getAllTopics();
    }

    @GetMapping("/get_this_topic")
    @ResponseStatus(HttpStatus.OK)
    public TopicResponse getThisTopic(@RequestParam("topicId") Long topicId) {
        return topicService.getThisTopic(topicId);
    }
}
