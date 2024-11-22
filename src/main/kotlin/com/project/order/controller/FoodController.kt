package com.project.order.controller

import com.project.order.data.Food
import com.project.order.data.Invoice
import com.project.order.service.FoodService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class FoodController(
    private val foodService: FoodService
) {
    @PostMapping("/add-foods")
    fun addFood(@RequestBody newFood: Food): FoodResponseDto {
        return FoodResponseDto(foodService.addFood(newFood))
    }

    @PutMapping("/update-food")
    fun updateFood(@RequestBody food: Food): FoodResponseDto {
        return FoodResponseDto((foodService.updateFood(food)))
    }

    @DeleteMapping("/delete-food")
    fun deleteFood(@RequestBody food: Food): FoodResponseDto {
        return FoodResponseDto(foodService.deleteFood(food))
    }
}

data class FoodResponseDto(
    val state: Boolean
)