package com.dv.ex.validation.controller;

import com.dv.ex.validation.model.FoodRequestModel;
import com.dv.ex.validation.model.FoodResponseModel;
import com.dv.ex.validation.service.FridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/fridge")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FridgeController {

    private final FridgeService fridgeService;

    @PostMapping("/food")
    public ResponseEntity<FoodResponseModel> addFoodToFridgeV1(@RequestBody FoodRequestModel request) {
        log.info("addFoodToFridgeV1: request=[{}]", request);
        return ResponseEntity.ok(fridgeService.addFoodToFridge(request));
    }

    @GetMapping("/food")
    public ResponseEntity<List<FoodResponseModel>> getAllFoodFromFridgeV1() {
        log.info("getAllFoodFromFridgeV1");
        return ResponseEntity.ok(fridgeService.getAllFoodFromFridge());
    }

    @GetMapping("/food/{category}")
    public ResponseEntity<List<FoodResponseModel>> getAllFoodFromFridgeByCategoryV1(@PathVariable String category) {
        log.info("getAllFoodFromFridgeByCategoryV1: category=[{}]", category);
        return ResponseEntity.ok(fridgeService.getAllFoodFromFridgeByCategory(category));
    }

    @PutMapping("/food")
    public ResponseEntity<FoodResponseModel> consumeFoodFromFridgeByNameV1(@RequestParam String name) {
        log.info("consumeFoodFromFridgeByNameV1: name=[{}]", name);
        return ResponseEntity.ok(fridgeService.consumeFoodByName(name));
    }

}
