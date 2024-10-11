package com.project.order.responsitory

import com.project.order.data.Food
import com.project.order.responsitory.base.BaseRepository

interface FoodRepository: BaseRepository<Food, Long> {
    fun findByCategory(category: String): List<Food>
}