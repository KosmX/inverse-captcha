package hu.bme.kszk.inverseCaptcha

import hu.bme.kszk.inverseCaptcha.generator.Token
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import java.util.Collections
import java.util.UUID

data class SessionID(
    val id: UUID = UUID.randomUUID()
)

data class Session(

    val solved: Int = 0,
    val current: Token? = null,
) {


    companion object {
        private val sessions : MutableMap<UUID, Session> = Collections.synchronizedMap(mutableMapOf<UUID, Session>())

        operator fun get(id: SessionID): Session = sessions[id.id] ?: Session()
        operator fun set(id: SessionID, session: Session) {
            sessions[id.id] = session
        }
    }
}

var PipelineContext<Unit, ApplicationCall>.session: Session
    get() = call.sessions.get<SessionID>()?.let{Session[it]} ?: Session()
    set(value) {
        val id = call.sessions.get<SessionID>() ?: SessionID().also { call.sessions.set<SessionID>(it) }
        Session[id] = value
    }
