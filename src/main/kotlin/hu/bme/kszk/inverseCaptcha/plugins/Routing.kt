package hu.bme.kszk.inverseCaptcha.plugins

import hu.bme.kszk.inverseCaptcha.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File


fun Application.configureRouting() {


    routing {
        staticResources("/static", "static")
        //staticFiles("/static", File("src/main/resources/static")) // TODO change this for release
        get("") {
            call.respondRedirect("/")
        }
        get("/") {
            createAndPostCaptcha()
        }

        post("/") {
            verifyAndPost()
        }

        get("/robots.txt") {
            call.respondRedirect("https://www.smbc-comics.com/comic/captcha")
        }

        get("/flag") {
            if (session.solved >= Util.requiredAmount) {
                if (session.solved == 69) {
                    call.respondText("Nice!")
                } else {
                    call.respondText(Util.flag)
                }
            } else {
                throw ForbiddenException("ARE YOU A ROBOT?")
            }
        }
    }

}

