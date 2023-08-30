package com.protoagora.choosesideservice.controller;

import com.protoagora.choosesideservice.dto.ChooseSideRequest;
import com.protoagora.choosesideservice.dto.ChooseSideResponse;
import com.protoagora.choosesideservice.model.ChooseSide;
import com.protoagora.choosesideservice.service.ChooseSideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/choose_side")
@RequiredArgsConstructor
@CrossOrigin
public class ChooseSideController {

    private final ChooseSideService chooseSideService;

    @PostMapping("/addChosenSide")
    @ResponseStatus(HttpStatus.CREATED)
    public void addChosenSide(@RequestBody ChooseSideRequest chooseSideRequest) { chooseSideService.addChosenSide(chooseSideRequest); }

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
