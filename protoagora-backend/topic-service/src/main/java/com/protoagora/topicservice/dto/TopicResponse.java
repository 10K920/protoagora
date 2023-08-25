package com.protoagora.topicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {
    private Long id;
    private Long userId;
    private Date postTimestamp;
    private String topicTitle;
    private String topicDescription;
    private String topicContent;
    private String option1;
    private int option1Count;
    private String option2;
    private int option2Count;
    private int opinionCount;
}
