package hu.bme.kszk.inverseCaptcha

import java.security.MessageDigest
import kotlin.time.Duration.Companion.seconds

object Util {
    val flag: String
    val requiredAmount = 64
    val allowedTime = requiredAmount.seconds/2

    val delta = 0.00000000001


    init {
        val md = MessageDigest.getInstance("MD5")
        md.update("1 AM A R0B0T".toByteArray())
        val hash = md.digest().toHex()
        flag = "SecurITeam{$hash}"
    }
}

fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
