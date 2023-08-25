package com.protoagora.choosesideservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChooseSideResponse {
    private Long id;
    private Long topicId;
    private Long userId;
    private Date chosenTimestamp;
    private Boolean side;
}
