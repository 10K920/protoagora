package com.protoagora.choosesideservice.controller;

import com.protoagora.choosesideservice.dto.ChooseSideRequest;
import com.protoagora.choosesideservice.dto.ChooseSideResponse;
import com.protoagora.choosesideservice.model.ChooseSide;
import com.protoagora.choosesideservice.service.ChooseSideService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/choose_side")
@RequiredArgsConstructor
@CrossOrigin()
public class ChooseSideController {

    private final ChooseSideService chooseSideService;
    @PostMapping("/addChosenSide")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "topic", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "topic")
    @Retry(name = "topic")
    public CompletableFuture<String> addChosenSide(@RequestBody ChooseSideRequest chooseSideRequest) {
        return CompletableFuture.supplyAsync(()->chooseSideService.addChosenSide(chooseSideRequest));
    }

    public CompletableFuture<String> fallbackMethod(ChooseSideRequest chooseSideRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()-> "Something went wrong, please choose side after some time!");
    }

    @GetMapping("/getThisTopicUserSide")
    @ResponseStatus(HttpStatus.OK)
    public ChooseSideResponse getThisTopicUserSide(@RequestParam("topicId") Long topicId, @RequestParam("userId") Long userId){
        return chooseSideService.getThisTopicUserSide(topicId, userId);
    }

    @GetMapping("/getUserSides")
    @ResponseStatus(HttpStatus.OK)
    public List<ChooseSideResponse> geUserSides(@RequestParam("userId") Long userId) {
        return chooseSideService.getUserSides(userId);
    }

    @GetMapping("/getThisTopicAllSides")
    @ResponseStatus(HttpStatus.OK)
    public List<ChooseSideResponse> getThisTopicAllSides(@RequestParam("topicId") Long topicId) {
        return chooseSideService.getThisTopicAllSides(topicId);
    }
}
