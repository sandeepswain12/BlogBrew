package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Blog {
    @Id
    private String id;
    private String title;
    @Column(length = 1000)
    private String content;
    @ManyToOne
    @JsonIgnore
    private User user;
}
