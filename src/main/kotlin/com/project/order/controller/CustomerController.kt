package com.project.order.controller

import com.project.order.data.Invoice
import com.project.order.dto.request.OrderFoodRequestDto
import com.project.order.service.CustomerService
import lombok.RequiredArgsConstructor
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class CustomerController(
    private val customerService: CustomerService,
    val messagingTemplate: SimpMessagingTemplate
) {
    @PostMapping("/order-food")
    fun orderFood(@RequestBody orderFoodRequestDto: OrderFoodRequestDto) {
        val idRestaurant = orderFoodRequestDto.id_restaurant
        messagingTemplate.convertAndSend("/admin/1", orderFoodRequestDto.invoice)
    }
}