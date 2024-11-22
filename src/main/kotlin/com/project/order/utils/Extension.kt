package com.project.order.utils

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


object Extension {
    private const val MAX_RANDOM: Long = 100000000000000000L
    private const val MIN_RANDOM: Long = 10000000000000000L
    private const val SECRET_NUMBER: Long = 135798671856L
    private val listRandom = mutableListOf<String>()
    private val listSecretNumber = mutableListOf<String>()

    fun randomNumber(): String {
        val lastNumber = if (listRandom.isEmpty()) MIN_RANDOM else listRandom[listRandom.size - 1].toLong()
        val newNumber = if (lastNumber < MAX_RANDOM) (lastNumber + SECRET_NUMBER).toString() else (MIN_RANDOM + SECRET_NUMBER).toString()
        listRandom.add(newNumber)
        return newNumber
    }

    fun checkSecretNumber(secretNumber: String): Boolean {
        return listRandom.contains(secretNumber)
    }

    fun handleImage(nameFood: String, image: String): String {
        try {
            val imageBytes: ByteArray = Base64.getDecoder().decode(image)
            val filePath = "../food_images/" + nameFood.lowercase() + ".png"
            val outputFile = File(filePath)
            FileOutputStream(outputFile).use { fos ->
                fos.write(imageBytes)
            }
            return filePath
        } catch (e: IOException) {
            LogUtil.log.error(e.toString())
            return ""
        }
    }
}

