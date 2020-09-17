package com.dv.ex.validation.repository;

import com.dv.ex.validation.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FridgeRepository extends JpaRepository<FoodEntity, Integer> {

    List<FoodEntity> findAllByCategory(String category);

    Optional<FoodEntity> findByName(String name);

}
