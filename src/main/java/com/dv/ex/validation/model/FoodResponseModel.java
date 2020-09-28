package com.dv.ex.validation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FoodResponseModel {

    private final String name;
    private final String category;
    private final int quantity;
    private final boolean refrigerated;

}
