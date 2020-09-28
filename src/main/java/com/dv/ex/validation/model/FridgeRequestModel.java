package com.dv.ex.validation.model;

import com.dv.ex.validation.validator.FoodCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FridgeRequestModel extends FoodRequestModel {

    @FoodCategory(allowed = {"dairy", "vegetables", "beer"})
    private String category;

}
