package com.dv.ex.validation.service;

import com.dv.ex.validation.entity.FoodEntity;
import com.dv.ex.validation.error.EntityNotFoundException;
import com.dv.ex.validation.model.FoodRequestModel;
import com.dv.ex.validation.model.FoodResponseModel;
import com.dv.ex.validation.repository.FridgeRepository;
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
public class FridgeServiceImpl implements FridgeService {

    private final FridgeRepository fridgeRepository;
    private final FoodEntityBuilder foodEntityBuilder;
    private final FoodResponseBuilder foodResponseBuilder;

    @Override
    public FoodResponseModel addFoodToFridge(FoodRequestModel request) {
        log.info("addFoodToFridge: request=[{}]", request);
        FoodEntity entity = foodEntityBuilder.forTheFridge(request);
        entity = fridgeRepository.save(entity);
        return foodResponseBuilder.fromEntity(entity);
    }

    @Override
    public List<FoodResponseModel> getAllFoodFromFridge() {
        log.info("getAllFoodFromFridge");
        return fridgeRepository.findAll().stream()
                .filter(FoodEntity::isRefrigerated)
                .map(foodResponseBuilder::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodResponseModel> getAllFoodFromFridgeByCategory(String category) {
        log.info("getAllFoodFromFridgeByCategory: category=[{}]", category);
        return fridgeRepository.findAllByCategory(category).stream()
                .filter(FoodEntity::isRefrigerated)
                .map(foodResponseBuilder::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FoodResponseModel consumeFoodByName(String name) {
        log.info("consumeFoodByName: name=[{}]", name);
        FoodEntity entity = fridgeRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Failed to retrieve food by the name of " + name));
        log.info("consumeFoodByName: entity=[{}]", entity);

        int quantity = entity.getQuantity();
        if (quantity > 0) {
            entity.setQuantity(--quantity);
        }

        if (entity.getQuantity() <= 0) {
            fridgeRepository.delete(entity);
            log.info("consumeFoodByName: deleted=[{}]", entity);
        }

        return foodResponseBuilder.fromEntity(entity);
    }

}
