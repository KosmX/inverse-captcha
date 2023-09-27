package hu.bme.kszk.inverseCaptcha

import hu.bme.kszk.inverseCaptcha.generator.Generator
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.coroutineScope
import java.time.Instant
import kotlin.time.toJavaDuration

object CaptchaHandler {
}


suspend fun PipelineContext<Unit, ApplicationCall>.createAndPostCaptcha(recreate: Boolean = false) = coroutineScope {

    if (session.dueTime <= Instant.now()) {
        session = session.copy(dueTime = Instant.now().plus(Util.allowedTime.toJavaDuration()))
    }

    val captcha = session.current?.takeIf { recreate } ?: Generator().generate()
    session = session.copy(current = captcha)

    call.respondTemplate("index.ftl", mapOf(
        "counter" to "${session.solved}/${Util.requiredAmount}",
        "chall" to captcha.asString,
        "time" to session.dueTime!!.toEpochMilli().toString()
    ))
}


