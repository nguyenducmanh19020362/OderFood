package com.project.order.responsitory

import com.project.order.data.Admin
import com.project.order.responsitory.base.BaseRepository

interface AdminRepository: BaseRepository<Admin, Long> {
    fun findByUsername(name: String): List<Admin>
    fun findByPassword(password: String): List<Admin>
}