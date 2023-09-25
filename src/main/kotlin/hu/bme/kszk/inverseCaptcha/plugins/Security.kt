package hu.bme.kszk.inverseCaptcha.plugins

import hu.bme.kszk.inverseCaptcha.Session
import hu.bme.kszk.inverseCaptcha.SessionID
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<SessionID>("42") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

}
