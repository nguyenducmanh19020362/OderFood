package com.project.order.data

import org.springframework.data.annotation.Id
import java.util.Date

data class Invoice (
    @Id
    var id: Long? = null,
    val tokenUser: String,
    val listFoods: List<Food>,
    val listCounter: Map<Long, Int>,
    val comment: String,
    var table: Int,
    var secret_number: Int,
    val date_created: Date
) {
    fun getTotalPrice(): Long {
        var totalPrice = 0L
        for(food in listFoods) {
            listCounter[food.id]?.let {
                totalPrice += it * food.price.toLong()
            }
        }
        return totalPrice
    }
}