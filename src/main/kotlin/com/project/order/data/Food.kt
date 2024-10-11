package com.project.order.data

import org.springframework.data.annotation.Id

data class Food (
    @Id
    var id: Long? = null,
    var name: String,
    var image_url: String,
    var price: String,
    var description: String,
    var discount: Int,
    var category: String
)
