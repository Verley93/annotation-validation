package com.dv.ex.validation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FoodRequestModel {

    private String name;
    private String category;
    private int quantity;
    private boolean refrigerated;

}
