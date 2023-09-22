package hu.bme.kszk.inverseCaptcha

import hu.bme.kszk.inverseCaptcha.generator.Generator

object TestGenerator {
    @JvmStatic
    fun main(args: Array<String>) {
        val stuff = Generator().generate()
        println(stuff)
        println(stuff.asString)
        println(stuff.result)
    }
}