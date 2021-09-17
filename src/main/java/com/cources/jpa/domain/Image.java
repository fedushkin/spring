package com.cources.jpa.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Entity.class)
    private Long id;

    @JsonView(View.Entity.class)
    private String fileName;

    @JsonView(View.EntityData.class)
    private String contentType;

    @JsonView(View.EntityData.class)
    private String extension;

    @JsonView(View.EntityData.class)
    private Long size;

    @JsonView(View.EntityData.class)
    private String hashId;

    @JsonView(View.Entity.class)
    private String uploadPath;

    @Enumerated(EnumType.STRING)
    @JsonView(View.EntityData.class)
    private ImageStatus imageStatus;
}