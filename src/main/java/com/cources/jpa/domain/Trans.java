package com.cources.jpa.domain;

import lombok.Data;

@Data
public class Trans {
    private Long id;

    private Double amount;

    private String reasons;

    private Long userId;
}