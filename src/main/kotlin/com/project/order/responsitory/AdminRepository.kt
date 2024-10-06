package com.project.order.responsitory

import com.project.order.data.Admin

interface AdminRepository: BaseRepository<Admin, Long> {
    fun findByUsername(name: String): List<Admin>
    fun findByPassword(password: String): List<Admin>
}