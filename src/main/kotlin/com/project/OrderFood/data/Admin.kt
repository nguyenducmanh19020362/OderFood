package com.project.OrderFood.data

import org.springframework.data.annotation.Id

data class Admin (
    @Id
    var id: Long,
    var username: String,
    var password: String,
)