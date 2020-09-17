package com.dv.ex.validation.service;

import com.dv.ex.validation.model.FoodRequestModel;
import com.dv.ex.validation.model.FoodResponseModel;

import java.util.List;

public interface FridgeService extends FoodService {

    /**
     * Adds food to the fridge
     *
     * @param request Request to add food
     * @return A {@code FoodResponseModel} containing the details of the food added
     */
    FoodResponseModel addFoodToFridge(FoodRequestModel request);

    /**
     * Returns all food contained in the fridge
     *
     * @return A list of {@code FoodResponseModel} containing the details of food in the fridge
     */
    List<FoodResponseModel> getAllFoodFromFridge();

    /**
     * @param category Food category
     * @return A list of {@code FoodResponseModel} containing the details of food in the fridge, filtered by {@code category}
     */
    List<FoodResponseModel> getAllFoodFromFridgeByCategory(String category);

}
