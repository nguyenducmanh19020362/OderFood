package com.project.order.service

import com.project.order.data.Food
import com.project.order.jwt.JwtTokenProvider
import com.project.order.responsitory.FoodRepository
import com.project.order.service.base.BaseService

class FoodService(
    jwtTokenProvider: JwtTokenProvider,
    private val foodRepository: FoodRepository
): BaseService(jwtTokenProvider) {

    private var listFoodsCache = mutableListOf<Food>()
    val listFood: MutableList<Food>
        get() {
            if (listFoodsCache.isEmpty()) listFoodsCache = foodRepository.findAll().toMutableList()
            return listFoodsCache
        }
    private var mapCategoryFoodsCache= mutableMapOf<String, List<Food>>()

    fun getAllFoods(): List<Food> {
        if (listFoodsCache.isEmpty()) listFoodsCache.addAll(foodRepository.findAll())
        return listFoodsCache
    }

    fun addFood(newFood: Food): Boolean {
        try {
            foodRepository.insert(newFood)
            if (listFoodsCache.isNotEmpty()) listFoodsCache.add(newFood)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun deleteFood(food: Food): Boolean {
        try {
            foodRepository.delete(food)
            if (listFoodsCache.isNotEmpty()) listFoodsCache.remove(food)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun updateFood(food: Food): Boolean {
        try {
            foodRepository.save(food)
            if (listFoodsCache.isNotEmpty()) {
                val oldFood = listFoodsCache.find { return it.id == food.id }
                listFoodsCache.remove(oldFood)
                listFoodsCache.add(food)
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun getFoodByCategory(category: String): List<Food>? {
        if (listFoodsCache.isEmpty()) {
            return foodRepository.findByCategory(category)
        }

        if (mapCategoryFoodsCache.containsKey(category)) {
            return mapCategoryFoodsCache[category]
        }

        val listFoodByCategory = mutableListOf<Food>()
        for (food in listFoodsCache) {
            if (food.category == category) {
                listFoodByCategory.add(food)
            }
        }
        mapCategoryFoodsCache[category] = listFoodByCategory
        return listFoodByCategory
    }
}