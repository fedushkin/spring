package com.cources.jpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.EntityData.class)
    private Long id;

    @JsonView(View.Entity.class)
    private String title;

    @JsonView(View.EntityData.class)
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy")
    @JsonView(View.Entity.class)
    private LocalDate createdDate;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private Image image;

    @PrePersist
    public void createdDate () {
        this.createdDate = LocalDate.now();
    }
}