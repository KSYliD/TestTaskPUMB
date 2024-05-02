package com.example.testtaskpumb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private String sex;
    private double weight;
    private double cost;
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    public Animal(String name, String type, String sex, double weight, double cost) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        if (weight > 0) {
            this.weight = weight;
        }
        if (cost > 0) {
            this.cost = cost;
        }
        if (cost >= 0 && cost <= 20) {
            this.category = Category.FIRST_CATEGORY;
        } else if (cost > 20 && cost <= 40) {
            this.category = Category.SECOND_CATEGORY;
        } else if (cost > 40 && cost <= 60) {
            this.category = Category.THIRD_CATEGORY;
        } else if (cost > 60) {
            this.category = Category.FOURTH_CATEGORY;
        }

    }


}
