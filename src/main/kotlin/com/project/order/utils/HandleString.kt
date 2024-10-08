package com.project.order.utils

import java.util.*

object HandleString {
    private const val MAX_RANDOM: Long = 100000000000000000L
    private const val MIN_RANDOM: Long = 10000000000000000L
    private val listRandom = mutableListOf<String>()

    fun randomNumber(): String {
        var randomNumber = ""
        val random = Random()
        if (listRandom.isEmpty()) {
            randomNumber = (random.nextLong(MAX_RANDOM) + MIN_RANDOM).toString()
            listRandom.add(randomNumber)
        }
        return randomNumber
    }

}
