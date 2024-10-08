package com.project.order.jwt

import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.text.ParseException
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtTokenProvider {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.expiration}")
    private val jwtExpiration: String? = null

    fun generateJWTToken(username: String, scope: String): String {
        val jwsHeader = JWSHeader((JWSAlgorithm.HS256))

        val jwtClaimsSet = JWTClaimsSet.Builder()
            .subject(username)
            .issuer("my.com")
            .issueTime(Date())
            .expirationTime(
                Date(Instant.now().plus(jwtExpiration!!.toLong(), ChronoUnit.HOURS).toEpochMilli())
            )
            .claim("scope", scope)
            .build()

        val jwtPayload = Payload(jwtClaimsSet.toJSONObject())
        val jwsObject = JWSObject(jwsHeader, jwtPayload)

        try {
            jwsObject.sign(MACSigner(jwtSecret))
            log.debug("jws token: {}", jwsObject.serialize())
            return jwsObject.serialize()
        } catch (e: JOSEException) {
            log.error("Cannot create token", e)
            throw RuntimeException(e)
        }
    }

    @Throws(JOSEException::class, ParseException::class)
    fun verifyToken(token: String?): Boolean {
        val verifier: JWSVerifier = MACVerifier(jwtSecret!!.toByteArray())
        val signedJWT = SignedJWT.parse(token)
        val expiration = signedJWT.jwtClaimsSet.expirationTime

        val verified = signedJWT.verify(verifier)
        return verified && expiration.after(Date())
    }
}