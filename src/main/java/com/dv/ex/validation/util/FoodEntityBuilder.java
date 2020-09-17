package com.dv.ex.validation.util;

import com.dv.ex.validation.entity.FoodEntity;
import com.dv.ex.validation.model.FoodRequestModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodEntityBuilder {

    private static final String CATEGORY_FRUIT = "fruit";
    private static final String CATEGORY_SNACKS = "snacks";
    private static final String CATEGORY_DAIRY = "dairy";

    public FoodEntity fromRequest(FoodRequestModel request) {
        return FoodEntity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .refrigerated(request.isRefrigerated())
                .build();
    }

    public List<FoodEntity> fromFridge() {
        return List.of(
                FoodEntity.builder()
                        .id(1)
                        .category(CATEGORY_FRUIT)
                        .name("blueberries")
                        .refrigerated(false)
                        .build(),
                FoodEntity.builder()
                        .id(2)
                        .category(CATEGORY_DAIRY)
                        .name("milk")
                        .refrigerated(true)
                        .build(),
                FoodEntity.builder()
                        .id(3)
                        .category(CATEGORY_DAIRY)
                        .name("yogurt")
                        .refrigerated(true)
                        .build(),
                FoodEntity.builder()
                        .id(4)
                        .category(CATEGORY_FRUIT)
                        .name("apples")
                        .refrigerated(false)
                        .build()
        );
    }

    public List<FoodEntity> fromPantry() {
        return List.of(
                FoodEntity.builder()
                        .id(5)
                        .category(CATEGORY_SNACKS)
                        .name("chips")
                        .refrigerated(false)
                        .build(),
                FoodEntity.builder()
                        .id(6)
                        .category(CATEGORY_SNACKS)
                        .name("cereal")
                        .refrigerated(false)
                        .build()
        );
    }

}
