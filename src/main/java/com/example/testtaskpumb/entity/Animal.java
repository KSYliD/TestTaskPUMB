package com.example.testtaskpumb;

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
        this.weight = weight;
        this.cost = cost;

        if (cost >= 0 && cost <= 20) {
            this.category = Category.firstCategory;
        } else if (cost > 20 && cost <= 40) {
            this.category = Category.secondCategory;
        }else if (cost > 40 && cost <= 60) {
            this.category = Category.thirdCategory;
        }else if (cost > 60) {
            this.category = Category.fourthCategory;
        }

    }




}
