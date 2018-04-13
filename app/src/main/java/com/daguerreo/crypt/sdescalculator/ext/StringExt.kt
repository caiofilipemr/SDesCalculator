package com.daguerreo.crypt.sdescalculator.ext

fun String.isABinaryRepresentation(): Boolean = !this.any { (it != '0') && (it != '1') }

infix private fun Int.absMod(divisor: Int): Int {
    val r = this.rem(divisor)
    return if (r < 0)  r + divisor else r
}

private fun String.shift(i: Int): String {
    val firstPosition = (i).absMod(this.length)
    val shiftSize = this.length - firstPosition
    return this.substring(shiftSize, this.length) + this.substring(0, shiftSize)
}

infix fun String.shiftLeft(i: Int) = this.shift(-i)

infix fun String.shiftRight(i: Int) = this.shift(i)