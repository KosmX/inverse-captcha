package hu.bme.kszk.inverseCaptcha

import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import hu.bme.kszk.inverseCaptcha.plugins.configureRouting
import hu.bme.kszk.inverseCaptcha.plugins.configureSecurity
import hu.bme.kszk.inverseCaptcha.plugins.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 80, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureRouting()
    configureStatusPages()
    configureTemplating()
}

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }
}
