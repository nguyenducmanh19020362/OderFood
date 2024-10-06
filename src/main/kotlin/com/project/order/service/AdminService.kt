package com.project.order.service

import com.project.order.constant.Const.StateResponse
import com.project.order.data.Admin
import com.project.order.dto.request.AdminRequestDto
import com.project.order.dto.response.AdminResponseDto
import com.project.order.jwt.JwtTokenProvider
import com.project.order.responsitory.AdminRepository
import com.project.order.service.base.BaseService
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val adminRepository: AdminRepository)
: BaseService(jwtTokenProvider) {

    fun login(adminRequestDto: AdminRequestDto): AdminResponseDto {
        val users: List<Admin> = adminRepository.findByUsername(adminRequestDto.username)
        var token = ""
        if (users.isNotEmpty() && users[0].password == adminRequestDto.password) {
            token = jwtTokenProvider.generateJWTToken(users[0].username)
            return AdminResponseDto(token, StateResponse.SUCCESS)
        }

        return AdminResponseDto(token, StateResponse.FAIL)
    }

    fun signup(adminRequestDto: AdminRequestDto): AdminResponseDto {
        val users: List<Admin> = adminRepository.findByUsername(adminRequestDto.username)
        var token = ""

        if (users.isEmpty()) {
            val newAdmin = Admin(
                username = adminRequestDto.username,
                password = adminRequestDto.password
            )

            adminRepository.insert(newAdmin)
            token = jwtTokenProvider.generateJWTToken(newAdmin.username)
            return AdminResponseDto(token, StateResponse.SUCCESS)
        }

        return AdminResponseDto(token, StateResponse.FAIL)
    }
}