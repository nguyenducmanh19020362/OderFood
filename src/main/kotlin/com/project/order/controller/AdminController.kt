package com.project.order.controller

import com.project.order.dto.request.AdminRequestDto
import com.project.order.dto.response.AdminResponseDto
import com.project.order.service.AdminService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class AdminController (
    private val adminService: AdminService
){

    @PostMapping("/login")
    fun login(@RequestBody adminRequestDto: AdminRequestDto): AdminResponseDto {
        return adminService.login(adminRequestDto)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody adminRequestDto: AdminRequestDto): AdminResponseDto {
        return adminService.signup(adminRequestDto)
    }
}
