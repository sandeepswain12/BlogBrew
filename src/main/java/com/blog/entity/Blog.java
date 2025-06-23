package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


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
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JsonIgnore
    private User user;

    @PrePersist
    public void onCreated(){
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
    @PreUpdate
    public void onModified(){
        this.modifiedDate = LocalDateTime.now();
    }



}
