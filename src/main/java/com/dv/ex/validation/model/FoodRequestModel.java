package com.dv.ex.validation.model;

import com.dv.ex.validation.validator.FoodQuantity;
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

    @FoodQuantity
    private int quantity;

    private boolean refrigerated;

}
