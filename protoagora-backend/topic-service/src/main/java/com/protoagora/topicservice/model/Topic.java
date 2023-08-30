package com.protoagora.topicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="t_topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @CreationTimestamp
    @Column(name = "post_timestamp")
    private Date postTimestamp;

    @Column(name = "topic_title")
    private String topicTitle;

    @Column(name = "topic_description")
    private String topicDescription;

    @Column(name = "topic_content")
    private String topicContent;

    @Column(name = "option_1")
    private String option1;

    @Column(name = "option_1_count")
    private int option1Count;

    @Column(name = "option_2")
    private String option2;

    @Column(name = "option_2_count")
    private int option2Count;

    @Column(name = "opinion_count")
    private int opinionCount;
}
