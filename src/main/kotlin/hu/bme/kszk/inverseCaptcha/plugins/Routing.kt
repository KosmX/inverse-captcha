package hu.bme.kszk.inverseCaptcha.plugins

import hu.bme.kszk.inverseCaptcha.ForbiddenException
import hu.bme.kszk.inverseCaptcha.Util
import hu.bme.kszk.inverseCaptcha.createAndPostCaptcha
import hu.bme.kszk.inverseCaptcha.session
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {


    routing {
        get("/") {
            createAndPostCaptcha()
        }

        get("/flag") {
            if (session.solved >= Util.requiredAmount) {
                call.respondText("SECURITEAM{${Util.flag}")
            } else {
                throw ForbiddenException("ARE YOU A ROBOT?")
            }
        }
    }

}

