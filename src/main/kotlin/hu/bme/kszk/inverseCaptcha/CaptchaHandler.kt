package hu.bme.kszk.inverseCaptcha

import hu.bme.kszk.inverseCaptcha.generator.Generator
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.coroutineScope
import java.time.Instant
import kotlin.math.abs
import kotlin.time.toJavaDuration


suspend fun PipelineContext<Unit, ApplicationCall>.createAndPostCaptcha(recreate: Boolean = false) = coroutineScope {

    if (session.dueTime <= Instant.now()) {
        session = Session(dueTime = Instant.now().plus(Util.allowedTime.toJavaDuration()))
    }

    if (recreate || session.current == null) {
        session = session.copy(current = Generator().generate())
    }
    val captcha = session.current!!

    call.respondTemplate("index.ftl", mapOf(
        "counter" to "${session.solved}/${Util.requiredAmount}",
        "chall" to captcha.asString,
        "time" to session.dueTime.toEpochMilli().toString()
    ))
}

suspend fun PipelineContext<Unit, ApplicationCall>.verifyAndPost() = coroutineScope {
    val solution = call.receiveParameters()["solution"]?.toDoubleOrNull()
        ?: throw BadRequestException("post parameter is not a number")

    if (session.dueTime < Instant.now()) {
        return@coroutineScope createAndPostCaptcha(true)
    }

    if (solution closeTo (session.current?.result) && session.dueTime >= Instant.now()) {
        session = session.copy(solved = session.solved + 1)

        if (session.solved >= Util.requiredAmount) {
            return@coroutineScope call.respondRedirect("/flag")
        }
    }
    createAndPostCaptcha(true)
}

private infix fun Double?.closeTo(other: Double?): Boolean {
    if (this == null || other == null) return false
    return abs(this - other) < this * Util.delta
}
