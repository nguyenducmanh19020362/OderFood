package com.project.order.utils

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


object Extension {
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

    fun checkRandomNumber(randomNumber: String): Boolean {
        return listRandom.remove(randomNumber)
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

