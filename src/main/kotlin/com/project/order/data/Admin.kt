package com.project.order.data

import com.project.order.constant.Const.Role
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