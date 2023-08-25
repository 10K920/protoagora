package com.protoagora.opinionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpinionRequest {
    private Long topicId;
    private Long userId;
    private String opinion;
    private Boolean side;
}
