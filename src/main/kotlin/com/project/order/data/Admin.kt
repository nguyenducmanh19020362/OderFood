package com.project.order.data

import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id

@NoArgsConstructor
@Setter
data class Admin (
    @Id
    var id: String? = null,
    var username: String,
    var password: String,
    var role: Set<String>
)