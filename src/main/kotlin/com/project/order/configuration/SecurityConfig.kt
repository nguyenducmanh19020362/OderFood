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
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableWebSecurity
class SecurityConfig {
    private final val publicEndpoints = arrayOf("/signup", "/login")
    private final val adminEndpoints = arrayOf("/random-number")

    @Value("\${jwt.secret}")
    private val secretKey: String? = null

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { request ->
            request.requestMatchers(HttpMethod.POST, *publicEndpoints).permitAll()
                .requestMatchers("/my-ws/**").permitAll()
                .requestMatchers(HttpMethod.GET, *adminEndpoints).hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        }

        httpSecurity.oauth2ResourceServer { oauth2 ->
            oauth2.jwt { jwtConfigure ->
                jwtConfigure.decoder(jwtDecoder())
                    .jwtAuthenticationConverter(jwtAuthenticationConverter())
            }
        }

        httpSecurity.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
        return httpSecurity.build()
    }

    /*
        Covert Authentication
        Example from Scope_Admin to Role_Admin
     */
    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_")

        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter)
        return jwtAuthenticationConverter
    }


    @Bean
    fun jwtDecoder(): JwtDecoder {
        val secretKeySpec = SecretKeySpec(secretKey!!.toByteArray(), "HS256")
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
            .macAlgorithm(MacAlgorithm.HS256)
            .build()
    }
}