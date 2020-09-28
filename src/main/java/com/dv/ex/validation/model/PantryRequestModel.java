package com.dv.ex.validation.model;

import com.dv.ex.validation.validator.FoodCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PantryRequestModel extends FoodRequestModel {

    @FoodCategory(allowed = {"grains", "canned", "snacks"})
    private String category;

}
