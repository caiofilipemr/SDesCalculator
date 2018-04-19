package com.daguerreo.crypt.sdescalculator.sdes

import com.daguerreo.crypt.sdescalculator.ext.*
import com.daguerreo.crypt.sdescalculator.utils.OperationResult
import com.daguerreo.crypt.sdescalculator.utils.Step

class SDesCrypt(val stringResource: SdesStringResource) {
    var steps = mutableListOf<Step>()

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

    fun encrypt(plainText: String, key: String): OperationResult {
        steps = mutableListOf()
        val (k1, k2) = generateSubKeys(key)
        val result = plainText.mapIndexed { index, it ->
            val x = process(it, k1, k2, index)
            steps.add(Step("${stringResource.encrypted.capitalize()} w[${index + 1}] = $x"))

            x.binToAscii()
        }.joinToString(separator = "") { it }

        steps.add(Step("${stringResource.encrypted.capitalize()} ${stringResource.text} = $result"))
        return OperationResult(result, steps)
    }

    fun decrypt(cryptedText: String, key: String): OperationResult {
        steps = mutableListOf()
        steps.add(Step("${stringResource.plainTextOrEncryptedText} ${stringResource.binRepresentation} = ${cryptedText.toBin(8, " ")}"))
        val (k1, k2) = generateSubKeys(key)
        val result = cryptedText.mapIndexed { index, it ->
            val x = process(it, k2, k1, index)
            steps.add(Step("${stringResource.decrypted.capitalize()} w[${index + 1}] = $x"))

            x.binToAscii()
        }.joinToString(separator = "") { it }

        steps.add(Step("${stringResource.decrypted.capitalize()} ${stringResource.text} = $result"))
        return OperationResult(result, steps)
    }

    private fun process(w: Char, k1: String, k2: String, index: Int): String {
        val ip = w.toBin(8).permut(ip)
        steps.add(Step("IP(w[${index + 1}]) = $ip"))

        val f1 = f(ip, k1)
        steps.add(Step("Fk = $f1"))

        val l1r1 = sw(f1)
        steps.add(Step("SW(Fk) = $l1r1"))

        val f2 = f(l1r1, k2)
        steps.add(Step("Fk = $f2"))

        val ip_i = f2.permut(ip_i)
        steps.add(Step("IP-¹(Fk) = $ip_i"))

        return ip_i
    }

    private fun generateSubKeys(key: String): Pair<String, String> {
        val permuted = key.permut(p10)
        steps.add(Step("P10(K) = $permuted"))

        val ls1 = Pair(permuted.substring(0, 5) shiftLeft 1, permuted.substring(5, 10) shiftLeft 1)
        steps.add(Step("LS-1 = ${ls1.first + ls1.second}"))

        val k1 = (ls1.first + ls1.second).permut(p8)
        steps.add(Step("P8(LS-1) = K1 = $k1"))

        val ls2 = Pair(ls1.first shiftLeft 2, ls1.second shiftLeft 2)
        steps.add(Step("LS-2 = ${ls2.first + ls2.second}"))

        val k2 = (ls2.first + ls2.second).permut(p8)
        steps.add(Step("P8(LS-2) = K2 = $k2"))

        return Pair(k1, k2)
    }

    private fun f(w: String, key: String): String {
        val (l0, r0) = w.pairSplit()
        steps.add(Step("L0 = $l0"))
        steps.add(Step("R0 = $r0"))

        val ep = r0.permut(ep)
        steps.add(Step("E/P(R0) = $ep"))

        val (s0, s1) = genSIndexes(ep, key)
        steps.add(Step("S0 = $s0"))
        steps.add(Step("S1 = $s1"))

        val p4 = getSValue(s0, s1).permut(p4)
        steps.add(Step("P4(S0S1) = $p4"))

        val lf = p4.binToByte() xor l0.binToByte()
        steps.add(Step("P4(S0S1) xor L0 = ${lf.toBin(4)}"))

        return lf.toBin(4) + r0
    }

    private fun genSIndexes(w: String, k: String): Pair<String, String> {
        val xor = w.binToByte() xor k.binToByte()
        steps.add(Step("E/P(R0) xor K1 = $xor"))

        return xor.toBin(8).pairSplit()
    }

    private fun getSValue(sl: String, sr: String): String {
        val slRow = getSRow(sl).binToByte()
        val slColumn = getSColumn(sl).binToByte()
        val srRow = getSRow(sr).binToByte()
        val srColumn = getSColumn(sr).binToByte()
        steps.add(Step("S0i = ${getSRow(sl)} ${stringResource.orInDecimal} $slRow"))
        steps.add(Step("S0j = ${getSRow(sl)} ${stringResource.orInDecimal} $slColumn"))
        steps.add(Step("S1i = ${getSRow(sr)} ${stringResource.orInDecimal} $srRow"))
        steps.add(Step("S1j = ${getSRow(sr)} ${stringResource.orInDecimal} $srColumn"))

        return s1[slRow * 4 + slColumn].toBin(2) + s2[srRow * 4 + srColumn].toBin(2)
    }

    private fun getSRow(s0: String) = (s0.substring(0, 1) + s0.substring(3, 4))

    private fun getSColumn(s0: String) = s0.substring(1, 3)

    private fun sw(str: String) = str.substring(4, 8) + str.substring(0, 4)
}