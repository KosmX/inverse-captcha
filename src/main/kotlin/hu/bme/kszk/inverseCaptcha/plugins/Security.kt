package hu.bme.kszk.inverseCaptcha.plugins

import hu.bme.kszk.inverseCaptcha.Session
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<Session>("42") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

}
