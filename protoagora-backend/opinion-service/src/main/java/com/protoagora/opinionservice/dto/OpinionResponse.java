package com.protoagora.opinionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpinionResponse {
    private Long id;
    private Long topicId;
    private Long userId;
    private Date commentTimestamp;
    private String opinion;
    private Boolean side;
    private int gppCount;
}
