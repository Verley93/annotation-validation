package com.dv.ex.validation.service;

import com.dv.ex.validation.entity.FoodEntity;
import com.dv.ex.validation.error.EntityNotFoundException;
import com.dv.ex.validation.model.FoodRequestModel;
import com.dv.ex.validation.model.FoodResponseModel;
import com.dv.ex.validation.repository.PantryRepository;
import com.dv.ex.validation.util.FoodEntityBuilder;
import com.dv.ex.validation.util.FoodResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PantryServiceImpl implements PantryService {

    private final PantryRepository pantryRepository;
    private final FoodEntityBuilder foodEntityBuilder;
    private final FoodResponseBuilder foodResponseBuilder;

    @Override
    public FoodResponseModel addFoodToPantry(FoodRequestModel request) {
        log.info("addFoodToPantry: request=[{}]", request);
        FoodEntity entity = foodEntityBuilder.forThePantry(request);
        entity = pantryRepository.save(entity);
        return foodResponseBuilder.fromEntity(entity);
    }

    @Override
    public List<FoodResponseModel> getAllFoodFromPantry() {
        log.info("getAllFoodFromPantry");
        return pantryRepository.findAll().stream()
                .filter(entity -> !entity.isRefrigerated())
                .map(foodResponseBuilder::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodResponseModel> getAllFoodFromPantryByCategory(String category) {
        log.info("getAllFoodFromPantryByCategory: category=[{}]", category);
        return pantryRepository.findAllByCategory(category).stream()
                .filter(entity -> !entity.isRefrigerated())
                .map(foodResponseBuilder::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FoodResponseModel consumeFoodByName(String name) {
        log.info("consumeFoodByName: name=[{}]", name);
        FoodEntity entity = pantryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Failed to retrieve food by the name of " + name));
        log.info("consumeFoodByName: entity=[{}]", entity);

        int quantity = entity.getQuantity();
        if (quantity > 0) {
            entity.setQuantity(--quantity);
        }

        if (entity.getQuantity() <= 0) {
            pantryRepository.delete(entity);
            log.info("consumeFoodByName: deleted=[{}]", entity);
        }

        return foodResponseBuilder.fromEntity(entity);
    }

}
