package com.dv.ex.validation.controller;

import com.dv.ex.validation.model.FoodResponseModel;
import com.dv.ex.validation.model.FridgeRequestModel;
import com.dv.ex.validation.service.FridgeService;
import com.dv.ex.validation.validator.FoodCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v1/fridge")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FridgeController {

    private final FridgeService fridgeService;

    @PostMapping("/food")
    public ResponseEntity<FoodResponseModel> addFoodToFridgeV1(
            @Valid @RequestBody FridgeRequestModel request) {

        log.info("addFoodToFridgeV1: request=[{}]", request);
        FoodResponseModel response = fridgeService.addFoodToFridge(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/food")
    public ResponseEntity<List<FoodResponseModel>> getAllFoodFromFridgeV1() {
        log.info("getAllFoodFromFridgeV1");
        return ResponseEntity.ok(fridgeService.getAllFoodFromFridge());
    }

    @GetMapping("/food/{category}")
    public ResponseEntity<List<FoodResponseModel>> getAllFoodFromFridgeByCategoryV1(
            @FoodCategory(allowed = {"dairy", "vegetables", "beer"}) @PathVariable String category) {

        log.info("getAllFoodFromFridgeByCategoryV1: category=[{}]", category);
        return ResponseEntity.ok(fridgeService.getAllFoodFromFridgeByCategory(category));
    }

    @PutMapping("/food")
    public ResponseEntity<FoodResponseModel> consumeFoodFromFridgeByNameV1(@RequestParam String name) {
        log.info("consumeFoodFromFridgeByNameV1: name=[{}]", name);
        return ResponseEntity.ok(fridgeService.consumeFoodByName(name));
    }

}
