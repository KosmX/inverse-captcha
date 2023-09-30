package hu.bme.kszk.inverseCaptcha.generator

import hu.bme.kszk.inverseCaptcha.generator.functions.Function
import hu.bme.kszk.inverseCaptcha.generator.functions.Functions
import java.security.SecureRandom

class Generator {

    private var depth: Int = 0

    companion object {
        const val MAX_DEPTH = 12

        val pool: List<Generator.() -> Token>

        val random: SecureRandom = SecureRandom.getInstanceStrong()

        init {
            val mutablePool = mutableListOf<Generator.() -> Token>()
            addOperators(mutablePool)
            Functions.addTrigonometry(mutablePool)


            pool = mutablePool
        }
    }


    fun generate(): Token {
        depth++

        return if (MAX_DEPTH >= depth) {
            run loop@{
                while (true) {
                    val next = pool.random(random)()
                    val token = if (next.result.isFinite() || next !is Function) {
                        next
                    } else {
                        Function(next.name, *next.tokens.map { Abs.wrap(it) }.toTypedArray(), program = next.program)
                    }
                    if (token.result.isFinite()) return@loop token
                }
            } as Token
        } else {
            Literal(random.nextGaussian()*1000)
        }.also { depth-- }
    }
}

fun <T> List<T>.random(random: SecureRandom): T = elementAt(random.nextInt(size))