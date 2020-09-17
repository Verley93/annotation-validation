package com.dv.ex.validation.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String category;
    private int quantity;
    private boolean refrigerated;

}
