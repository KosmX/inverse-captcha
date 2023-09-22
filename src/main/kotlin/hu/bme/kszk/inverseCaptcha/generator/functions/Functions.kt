package hu.bme.kszk.inverseCaptcha.generator.functions

import hu.bme.kszk.inverseCaptcha.generator.Generator
import hu.bme.kszk.inverseCaptcha.generator.Token
import kotlin.math.*

object Functions {
    fun addTrigonometry(pool: MutableList<Generator.() -> Token>) {

        pool += Function.single("sin") { sin(it) }
        pool += Function.single("cos") { cos(it) }
        pool += Function.single("tan") { tan(it) }
        pool += Function.bi("atan2", ::atan2)
        pool += Function.single("sinh", ::sinh)
        pool += Function.single("cosh", ::cosh)
        //pool += Function.bi("rem") { a, b -> a % b }
        pool += Function.single("ln", ::ln)
        pool += Function.single("log10", ::log10)
        pool += Function.single("log2", ::log2)
        pool += Function.single("sqrt", ::sqrt)
        pool += Function.single("exp", ::exp)


        pool += Function.bi("pow") { a, b -> a.pow(b) }


    }
}
