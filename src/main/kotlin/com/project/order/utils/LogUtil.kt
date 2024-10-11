package com.project.order.utils

import com.project.order.jwt.JwtTokenProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LogUtil {
    companion object {
        val log: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }
}