package hu.bme.kszk.inverseCaptcha

import hu.bme.kszk.inverseCaptcha.plugins.configureRouting
import hu.bme.kszk.inverseCaptcha.plugins.configureSecurity
import hu.bme.kszk.inverseCaptcha.plugins.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureRouting()
    configureStatusPages()
}
