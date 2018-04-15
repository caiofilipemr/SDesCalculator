package com.daguerreo.crypt.sdescalculator.sdes

import com.daguerreo.crypt.sdescalculator.ext.*

class SDesCrypt {
    companion object {
        private val p10 = arrayOf(3, 5, 2, 7, 4, 10, 1, 9, 8, 6)
        private val p8 = arrayOf(6, 3, 7, 4, 8, 5, 10, 9)
        private val p4 = arrayOf(2, 4, 3, 1)
        private val s1 = arrayOf(1, 0, 3, 2, 3, 2, 1, 0, 0, 2, 1, 3, 3, 1, 3, 2)
        private val s2 = arrayOf(0, 1, 2, 3, 2, 0, 1, 3, 3, 0, 1, 0, 2, 1, 0, 3)
        private val ip = arrayOf(2, 6, 3, 1, 4, 8, 5, 7)
        private val ip_i = arrayOf(4, 1, 3, 5, 7, 2, 8, 6)
        private val ep = arrayOf(4, 1, 2, 3, 2, 3, 4, 1)

        fun validKey(key: String) = key.isABinaryRepresentation() && key.length == 10
    }

    fun crypt(plainText: String, key: String): String {
        val (k1, k2) = generateSubKeys(key)
        return plainText.map {
            f(sw(f(it.toString().permut(ip), k1)), k2).permut(ip_i)
        }.toString()
    }

    fun decrypt(cryptedText: String, key: String) {
        val (k1, k2) = generateSubKeys(key)

    }

    private fun generateSubKeys(key: String): Pair<String, String> {
        val ls1 = Pair(key.substring(0, 5) shiftLeft 1, key.substring(5, 10) shiftLeft 1)
        val k1 = (ls1.first + ls1.second).permut(p8)
        val ls2 = Pair(ls1.first shiftLeft 2, ls1.second shiftLeft 2)
        val k2 = (ls2.first + ls2.second).permut(p8)
        return Pair(k1, k2)
    }

    private fun f(w: String, key: String): String {
        val (l0, r0) = w.pairSplit()
        val (s0, s1) = genSIndexes(r0.permut(ep), key)
        val lf = getSValue(s0, s1).permut(p4).binToByte() xor l0.binToByte()
        return lf.toBin() + r0
    }

    private fun genSIndexes(w: String, k: String): Pair<String, String> {
        val xor = w.binToByte() xor k.binToByte()
        return xor.toBin().pairSplit()
    }

    private fun getSValue(sl: String, sr: String): String {
        val slRow = getSRow(sl)
        val slColumn = getSColumn(sl)
        val srRow = getSRow(sr)
        val srColumn = getSColumn(sr)
        return s1[slRow * 4 + slColumn].toBin() + s2[srRow * 4 + srColumn].toBin()
    }

    private fun getSRow(s0: String) =
            (s0.substring(0, 1) + s0.substring(3, 4)).binToByte()

    private fun getSColumn(s0: String) = s0.substring(1, 3).binToByte()

    private fun sw(str: String): String {
        return str.substring(4, 8) + str.substring(0, 4)
    }
}