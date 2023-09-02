package com.protoagora.opinionservice.controller;

import com.protoagora.opinionservice.dto.OpinionRequest;
import com.protoagora.opinionservice.dto.OpinionResponse;
import com.protoagora.opinionservice.service.OpinionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/opinion")
@RequiredArgsConstructor
@CrossOrigin
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "topic", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "topic")
    @Retry(name = "topic")
    public CompletableFuture<String> addOpinion(@RequestBody OpinionRequest opinionRequest) {
        return CompletableFuture.supplyAsync(()->opinionService.addOpinion(opinionRequest));
    }

    public CompletableFuture<String> fallbackMethod(OpinionRequest opinionRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->"Something went wrong, please submit opinion after some time!");
    }

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
