package com.protoagora.choosesideservice.service;

import com.protoagora.choosesideservice.dto.ChooseSideRequest;
import com.protoagora.choosesideservice.dto.ChooseSideResponse;
import com.protoagora.choosesideservice.model.ChooseSide;
import com.protoagora.choosesideservice.repository.ChooseSideRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChooseSideService {

    private final ChooseSideRepository chooseSideRepository;
    private final WebClient.Builder webClientBuilder;

    public String addChosenSide(ChooseSideRequest chooseSideRequest){
        ChooseSide chooseSide = new ChooseSide();
        chooseSide.setTopicId(chooseSideRequest.getTopicId());
        chooseSide.setUserId(chooseSideRequest.getUserId());
        chooseSide.setSide(chooseSideRequest.getSide());

        // Call topic service
        // to update the count for the option chosen by the user
        Boolean option = chooseSideRequest.getSide();
        String topicId = chooseSideRequest.getTopicId().toString();
        String apiUrl = "http://topic-service/api/topic/";

        if (option) {
            apiUrl += "incrementOption1";
        }
        else {
            apiUrl += "incrementOption2";
        }

        Boolean result = webClientBuilder.build().get()
                .uri(apiUrl, uriBuilder -> uriBuilder.queryParam("topicId", topicId).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            chooseSideRepository.save(chooseSide);
            log.info("{} choose side {}", chooseSide.getUserId(), chooseSide.getSide());
            return "Side Chosen Successfully";
        }
        else {
            throw new IllegalArgumentException("topicId is invalid!");
        }
    }

    public ChooseSideResponse getThisTopicUserSide(Long topicId, Long userId) {
        ChooseSide chooseSide = chooseSideRepository.findByTopicIdAndUserId(topicId, userId);
        ChooseSideResponse chooseSideResponse = new ChooseSideResponse();
        chooseSideResponse.setId(chooseSide.getId());
        chooseSideResponse.setTopicId(chooseSide.getTopicId());
        chooseSideResponse.setUserId(chooseSide.getUserId());
        chooseSideResponse.setChosenTimestamp(chooseSide.getChosenTimestamp());
        chooseSideResponse.setSide(chooseSide.getSide());

        return chooseSideResponse;
    }

    public List<ChooseSideResponse> getUserSides(Long userId){
        List<ChooseSide> sides = chooseSideRepository.findAllByUserId(userId);
        return sides.stream().map(this::mapToChooseSideResponse).toList();

    }

    public List<ChooseSideResponse> getThisTopicAllSides(Long topicId){
        List<ChooseSide> sides = chooseSideRepository.findAllByTopicId(topicId);

        return sides.stream().map(this::mapToChooseSideResponse).toList();
    }

    private ChooseSideResponse mapToChooseSideResponse(ChooseSide chooseSide){
        ChooseSideResponse chooseSideResponse = new ChooseSideResponse();
        chooseSideResponse.setId(chooseSide.getId());
        chooseSideResponse.setTopicId(chooseSide.getTopicId());
        chooseSideResponse.setUserId(chooseSide.getUserId());
        chooseSideResponse.setChosenTimestamp(chooseSide.getChosenTimestamp());
        chooseSideResponse.setSide(chooseSide.getSide());

        return chooseSideResponse;
    }
}
