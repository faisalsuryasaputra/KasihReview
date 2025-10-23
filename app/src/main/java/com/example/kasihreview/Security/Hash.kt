package com.example.kasihreview.Security
import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class Hash {
    fun generateSalt(length: Int = 16): ByteArray {
        val salt = ByteArray(length)
        SecureRandom().nextBytes(salt)
        return salt
    }

    fun hashPasswordPBKDF2(
        password: String,
        salt: ByteArray,
        iterations: Int = 100_000,
        keyLength: Int = 256
    ): String {
        val spec = PBEKeySpec(password.toCharArray(), salt, iterations, keyLength)
        val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val key = skf.generateSecret(spec).encoded
        return Base64.encodeToString(key, Base64.NO_WRAP)
    }

    fun verifyPassword(
        passwordAttempt: String,
        storedHashBase64: String,
        salt: ByteArray,
        iterations: Int = 100_000,
        keyLength: Int = 256
    ): Boolean {
        val attemptHash = hashPasswordPBKDF2(passwordAttempt, salt, iterations, keyLength)
        return constantTimeEquals(attemptHash, storedHashBase64)
    }

    // constant-time comparison to reduce timing attacks
    fun constantTimeEquals(a: String, b: String): Boolean {
        if (a.length != b.length) return false
        var result = 0
        for (i in a.indices) {
            result = result or (a[i].code xor b[i].code)
        }
        return result == 0
    }
}