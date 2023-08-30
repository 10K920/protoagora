package com.protoagora.choosesideservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="t_choose_side")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChooseSide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "user_id")
    private Long userId;

    @CreationTimestamp
    @Column(name = "chosen_timestamp")
    private Date chosenTimestamp;

    @Column(name = "side")
    private Boolean side;
}
