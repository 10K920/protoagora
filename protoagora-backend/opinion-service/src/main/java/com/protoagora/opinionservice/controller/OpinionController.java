package com.protoagora.opinionservice.controller;

import com.protoagora.opinionservice.dto.OpinionRequest;
import com.protoagora.opinionservice.dto.OpinionResponse;
import com.protoagora.opinionservice.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opinion")
@RequiredArgsConstructor
@CrossOrigin
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOpinion(@RequestBody OpinionRequest opinionRequest) { opinionService.addOpinion(opinionRequest); }

    @GetMapping("/getUserOpinions")
    @ResponseStatus(HttpStatus.OK)
    public List<OpinionResponse> getUserOpinions(@RequestParam("userId") Long userId) {
        return opinionService.getUserOpinions(userId);
    }

    @GetMapping("/getThisTopicAllOpinions")
    @ResponseStatus(HttpStatus.OK)
    public List<OpinionResponse> getThisTopicAllOpinions(@RequestParam("topicId") Long topicId) {
        return opinionService.getThisTopicAllOpinions(topicId);
    }

    @GetMapping("/getThisTopicOption1Opinions")
    @ResponseStatus(HttpStatus.OK)
    public List<OpinionResponse> getThisTopicOption1Opinions(@RequestParam("topicId") Long topicId) {
        return opinionService.getThisTopicOption1Opinions(topicId);
    }

    @GetMapping("/getThisTopicOption2Opinions")
    @ResponseStatus(HttpStatus.OK)
    public List<OpinionResponse> getThisTopicOption2Opinions(@RequestParam("topicId") Long topicId) {
        return opinionService.getThisTopicOption2Opinions(topicId);
    }

}
