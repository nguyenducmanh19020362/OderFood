package com.project.order.controller

import com.project.order.dto.request.AdminRequestDto
import com.project.order.dto.response.AdminResponseDto
import com.project.order.dto.response.RandomNumberResponseDto
import com.project.order.service.AdminService
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class AdminController (
    private val adminService: AdminService
){
    private val log = LoggerFactory.getLogger(this.javaClass)
    @PostMapping("/login")
    fun login(@RequestBody adminRequestDto: AdminRequestDto): AdminResponseDto {
        return adminService.login(adminRequestDto)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody adminRequestDto: AdminRequestDto): AdminResponseDto {
        return adminService.signup(adminRequestDto)
    }

    @GetMapping("/random-number")
    fun randomNumber(): RandomNumberResponseDto {
        return RandomNumberResponseDto(adminService.randomNumber())
    }

}
