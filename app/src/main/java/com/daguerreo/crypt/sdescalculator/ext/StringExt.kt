package com.daguerreo.crypt.sdescalculator.ext

fun String.isABinaryRepresentation(): Boolean = !any { (it != '0') && (it != '1') } && isNotEmpty()

infix fun Int.absMod(divisor: Int): Int {
    val r = rem(divisor)
    return if (r < 0)  r + divisor else r
}

private fun String.shift(i: Int): String {
    val firstPosition = (i).absMod(length)
    val shiftSize = length - firstPosition
    return substring(shiftSize, length) + substring(0, shiftSize)
}

infix fun String.shiftLeft(i: Int) = shift(-i)

infix fun String.shiftRight(i: Int) = shift(i)

fun String.permut(permutationArray: Array<Int>): String {
    val sb = StringBuilder()
    permutationArray.forEach { sb.append(this[it - 1]) }
    return sb.toString()
}

fun Int.toBin(): String {
    val sb = StringBuilder()
    var bin = this
    while (bin > 0) {
        sb.append(bin % 2)
        bin = bin shr 1
    }
    return if (sb.isNotEmpty()) sb.toString().reversed() else "0"
}

fun Int.toBin(minLength: Int): String {
    val bin = toBin()
    return if (bin.length >= minLength) bin else "0".repeat(minLength - bin.length) + bin
}

fun Char.toBin() = toInt().toBin()

fun Char.toBin(minLength: Int) = toInt().toBin(minLength)

fun String.binToByte(): Int {
    var byte = 0
    forEach { byte = (byte + (it - '0')) shl 1 }
    return byte shr 1
}

fun String.pairSplit(): Pair<String, String> {
    return Pair(substring(0, length shr 1), substring(length shr 1, length))
}

fun String.binToAscii() = binToByte().toChar().toString()