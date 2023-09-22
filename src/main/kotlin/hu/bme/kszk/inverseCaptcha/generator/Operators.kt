package hu.bme.kszk.inverseCaptcha.generator

import kotlin.math.abs


fun addOperators(pool: MutableList<Generator.() -> Token>) {
    pool += { Addition(generate(), generate()) }
    pool += { Subtraction(generate(), generate()) }
    pool += { Multiplication(generate(), generate()) }
    pool += { Division(generate(), generate()) }
    pool += { UnaryMinus.wrap(generate()) }
    pool += { Abs.wrap(generate()) }
}


class Addition(rhs: Token, lhs: Token) : Operator("+", rhs, lhs) {
    override val result: Double
        get() = rhs.result + lhs.result
}

class Subtraction(rhs: Token, lhs: Token) : Operator("-", rhs, lhs) {
    override val result: Double
        get() = rhs.result - lhs.result
}

class Multiplication(rhs: Token, lhs: Token) : Operator("*", rhs, lhs) {
    override val result: Double
        get() = rhs.result * lhs.result
}

class Division(rhs: Token, lhs: Token) : Operator("/", rhs, lhs) {
    override val result: Double
        get() = rhs.result / lhs.result
}

class UnaryMinus private constructor(val token: Token) : Token {
    companion object {
        fun wrap(token: Token) : Token {
            return when (token) {
                is UnaryMinus -> token.token
                is Literal -> Literal(-token.result)
                else -> UnaryMinus(token)
            }
        }
    }

    override val asString: String
        get() = "-${token.asString}"

    override val result: Double
        get() = -token.result

}

class Abs private constructor(private val token: Token) : Token {
    companion object {
        fun wrap(token: Token) : Token {
            return when(token) {
                is Abs -> token
                else -> Abs(token)
            }
        }
    }

    override val asString: String
        get() = "abs(${token.asString})"

    override val result: Double
        get() = abs(token.result)
}
