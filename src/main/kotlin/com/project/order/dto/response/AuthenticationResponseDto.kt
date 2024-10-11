package com.project.order.dto.response

import com.project.order.data.Food

data class AuthenticationResponseDto (
    val listFoods: List<Food>,
    val token: String,
    val authentication: Boolean
)