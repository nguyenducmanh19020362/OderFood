package com.project.order.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableWebSecurity
class SecurityConfig {
    private final val publicEndpoints = arrayOf("/signup", "/login")

    @Value("\${jwt.secret}")
    private val secretKey: String? = null

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { request ->
            request.requestMatchers(HttpMethod.POST, *publicEndpoints).permitAll()
                .anyRequest().authenticated()
        }

        httpSecurity.oauth2ResourceServer { oauth2 ->
            oauth2.jwt { jwtConfigure ->
                jwtConfigure.decoder(
                    jwtDecoder()
                )
            }
        }

        httpSecurity.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
        return httpSecurity.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val secretKeySpec = SecretKeySpec(secretKey!!.toByteArray(), "HS512")
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
            .macAlgorithm(MacAlgorithm.HS512)
            .build()
    }
}