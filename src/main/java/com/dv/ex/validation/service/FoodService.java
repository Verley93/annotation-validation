package com.dv.ex.validation.service;

import com.dv.ex.validation.model.FoodResponseModel;

public interface FoodService {

    /**
     * Updates the quantity of the food eaten.  If the quantity reaches zero, the entity is deleted
     *
     * @param name Name of the food we'd like to eat
     * @return A {@code FoodResponseModel} containing the details of the updated state of the food eaten
     */
    FoodResponseModel consumeFoodByName(String name);

}
