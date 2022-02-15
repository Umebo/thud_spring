package com.igniemie.thud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@AllArgsConstructor
@Getter
public enum PlayerType {
    X(1),
    O(2);

    private Integer value;
}
