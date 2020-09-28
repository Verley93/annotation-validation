package com.dv.ex.validation.util;

import com.dv.ex.validation.entity.FoodEntity;
import com.dv.ex.validation.model.FridgeRequestModel;
import com.dv.ex.validation.model.PantryRequestModel;
import org.springframework.stereotype.Component;

@Component
public class FoodEntityBuilder {

    public FoodEntity forTheFridge(FridgeRequestModel request) {
        return FoodEntity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .refrigerated(request.isRefrigerated())
                .build();
    }

    public FoodEntity forThePantry(PantryRequestModel request) {
        return FoodEntity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .refrigerated(request.isRefrigerated())
                .build();
    }

}
