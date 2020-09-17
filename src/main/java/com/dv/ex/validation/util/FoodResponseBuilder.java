package com.dv.ex.validation.util;

import com.dv.ex.validation.entity.FoodEntity;
import com.dv.ex.validation.model.FoodResponseModel;
import org.springframework.stereotype.Component;

@Component
public class FoodResponseBuilder {

    public FoodResponseModel fromEntity(FoodEntity entity) {
        return FoodResponseModel.builder()
                .name(entity.getName())
                .category(entity.getCategory())
                .quantity(entity.getQuantity())
                .refrigerated(entity.isRefrigerated())
                .build();
    }

}
