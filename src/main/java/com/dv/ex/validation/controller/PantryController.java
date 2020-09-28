package com.dv.ex.validation.controller;

import com.dv.ex.validation.model.FoodResponseModel;
import com.dv.ex.validation.model.PantryRequestModel;
import com.dv.ex.validation.service.PantryService;
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
@RequestMapping(path = "/api/v1/pantry")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PantryController {

    private final PantryService pantryService;

    @Validated
    @PostMapping("/food")
    public ResponseEntity<FoodResponseModel> addFoodToPantryV1(@Valid @RequestBody PantryRequestModel request) {
        log.info("addFoodToPantryV1: request=[{}]", request);
        return ResponseEntity.ok(pantryService.addFoodToPantry(request));
    }

    @GetMapping("/food")
    public ResponseEntity<List<FoodResponseModel>> getAllFoodFromPantryV1() {
        log.info("getAllFoodFromPantryV1");
        return ResponseEntity.ok(pantryService.getAllFoodFromPantry());
    }

    @GetMapping("/food/{category}")
    public ResponseEntity<List<FoodResponseModel>> getAllFoodFromPantryByCategoryV1(@PathVariable String category) {
        log.info("getAllFoodFromPantryByCategoryV1: category=[{}]", category);
        return ResponseEntity.ok(pantryService.getAllFoodFromPantryByCategory(category));
    }

    @PutMapping("/food")
    public ResponseEntity<FoodResponseModel> consumeFoodFromPantryByNameV1(@RequestParam String name) {
        log.info("consumeFoodFromPantryByNameV1: name=[{}]", name);
        return ResponseEntity.ok(pantryService.consumeFoodByName(name));
    }

}
