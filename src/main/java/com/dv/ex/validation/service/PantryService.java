package com.dv.ex.validation.service;

import com.dv.ex.validation.model.FoodResponseModel;
import com.dv.ex.validation.model.PantryRequestModel;

import java.util.List;

public interface PantryService extends FoodService {

    /**
     * Adds food to the pantry
     *
     * @param request Request to add food
     * @return A {@code FoodResponseModel} containing the details of the food added
     */
    FoodResponseModel addFoodToPantry(PantryRequestModel request);

    /**
     * Returns all food contained in the pantry
     *
     * @return A list of {@code FoodResponseModel} containing the details of food in the pantry
     */
    List<FoodResponseModel> getAllFoodFromPantry();

    /**
     * @param category Food category
     * @return A list of {@code FoodResponseModel} containing the details of food in the pantry, filtered by {@code category}
     */
    List<FoodResponseModel> getAllFoodFromPantryByCategory(String category);

}
