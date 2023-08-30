package com.protoagora.opinionservice.service;

import com.protoagora.opinionservice.dto.OpinionRequest;
import com.protoagora.opinionservice.dto.OpinionResponse;
import com.protoagora.opinionservice.model.Opinion;
import com.protoagora.opinionservice.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final WebClient.Builder webClientBuilder;

    public void addOpinion(OpinionRequest opinionRequest){
        Opinion opinion = new Opinion();
        opinion.setTopicId(opinionRequest.getTopicId());
        opinion.setUserId(opinionRequest.getUserId());
        opinion.setOpinion(opinionRequest.getOpinion());
        opinion.setSide(opinionRequest.getSide());
        opinion.setGppCount(0);

        // Call topic service
        // to update the count for the option chosen by the user
        String topicId = opinionRequest.getTopicId().toString();
        String apiUrl = "http://topic-service/api/topic/incrementOpinionCount";

        Boolean result = webClientBuilder.build().get()
                .uri(apiUrl, uriBuilder -> uriBuilder.queryParam("topicId", topicId).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            opinionRepository.save(opinion);
        }
        else {
            throw new IllegalArgumentException("topicId is invalid!");
        }

        log.info("{} added opinion to side {}", opinion.getUserId(), opinion.getSide());
    }

    public List<OpinionResponse> getUserOpinions(Long userId){
        List<Opinion> opinions = opinionRepository.findAllByUserId(userId);

        return opinions.stream().map(this::mapToOpinionResponse).toList();
    }

    public List<OpinionResponse> getThisTopicAllOpinions(Long topicId){
        List<Opinion> opinions = opinionRepository.findAllByTopicId(topicId);

        return opinions.stream().map(this::mapToOpinionResponse).toList();
    }

    public List<OpinionResponse> getThisTopicOption1Opinions(Long topicId){
        List<Opinion> opinions = opinionRepository.findAllByTopicIdAndSide(topicId, true);
        return opinions.stream().map(this::mapToOpinionResponse).toList();
    }

    public List<OpinionResponse> getThisTopicOption2Opinions(Long topicId){
        List<Opinion> opinions = opinionRepository.findAllByTopicIdAndSide(topicId, false);
        return opinions.stream().map(this::mapToOpinionResponse).toList();
    }

    private OpinionResponse mapToOpinionResponse(Opinion opinion) {
        OpinionResponse opinionResponse = new OpinionResponse();
        opinionResponse.setId(opinion.getId());
        opinionResponse.setTopicId(opinion.getTopicId());
        opinionResponse.setUserId(opinion.getUserId());
        opinionResponse.setCommentTimestamp(opinion.getCommentTimestamp());
        opinionResponse.setOpinion(opinion.getOpinion());
        opinionResponse.setSide(opinion.getSide());
        opinionResponse.setGppCount(opinion.getGppCount());

        return opinionResponse;
    }
}
