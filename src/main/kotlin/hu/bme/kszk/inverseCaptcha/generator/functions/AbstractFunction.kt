package hu.bme.kszk.inverseCaptcha.generator.functions

import hu.bme.kszk.inverseCaptcha.generator.Generator
import hu.bme.kszk.inverseCaptcha.generator.Token

class Function(val name: String, vararg val tokens: Token, val program: Function.() -> Double) : Token {

    companion object {
        fun single(name: String, program: (Double) -> Double): Generator.() -> Token = {
            Function(name, generate()) { program(tokens[0].result) }
        }

        fun bi(name: String, program: (Double, Double) -> Double): Generator.() -> Token = {
            Function(name, generate(), generate()) { program(tokens[0].result, tokens[1].result) }
        }
    }

    override val asString: String
        get() = "$name(${tokens.joinToString(separator = ", ") { it.asString }})"

    override val result: Double
        get() = program()
}

