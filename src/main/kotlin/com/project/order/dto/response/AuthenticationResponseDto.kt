package com.project.order.dto.response

data class AuthenticationResponseDto (
    val token: String,
    val authentication: Boolean
)