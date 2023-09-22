package hu.bme.kszk.inverseCaptcha

import hu.bme.kszk.inverseCaptcha.generator.Token
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*

data class Session(
    val solved: Int = 0,
    val current: Token? = null,
)

var PipelineContext<Unit, ApplicationCall>.session: Session
    get() = call.sessions.get<Session>() ?: Session()
    set(value) = call.sessions.set(value)
