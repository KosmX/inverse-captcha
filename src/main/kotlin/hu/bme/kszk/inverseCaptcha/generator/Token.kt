package hu.bme.kszk.inverseCaptcha.generator

interface Token {
    val asString: String
    val result: Double
}

abstract class Operator(
    private val symbol: String,
    protected val rhs: Token,
    protected val lhs: Token,
) : Token {
    override val asString: String
        get() = "(${rhs.asString} $symbol ${lhs.asString})"

    override fun toString(): String {
        return "Operator(rhs=$rhs, lhs=$lhs)"
    }
}

data class Literal(override val result: Double) : Token {
    override val asString: String
        get() = result.toString()
}
