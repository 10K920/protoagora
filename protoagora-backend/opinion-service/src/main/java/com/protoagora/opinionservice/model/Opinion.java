package com.protoagora.opinionservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="t_opinions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "user_id")
    private Long userId;

    @CreationTimestamp
    @Column(name = "comment_timestamp")
    private Date commentTimestamp;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "side")
    private Boolean side;

    @Column(name = "gpp_count")
    private int gppCount;
}
