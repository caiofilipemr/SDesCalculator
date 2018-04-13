package com.daguerreo.crypt.sdescalculator.sdes

import com.daguerreo.crypt.sdescalculator.ext.isABinaryRepresentation
import com.daguerreo.crypt.sdescalculator.ext.shiftLeft

class SDesCrypt {
    companion object {
        private val p10 = arrayOf(3, 5, 2, 7, 4, 10, 1, 9, 8, 6)
        private val p8 = arrayOf(6, 3, 7, 4, 8, 5, 10, 9)
        private val s1 = arrayOf(1, 0, 3, 2, 3, 2, 1, 0, 0, 2, 1, 3, 3, 1, 3, 2)
        private val s2 = arrayOf(0, 1, 2, 3, 2, 0, 1, 3, 3, 0, 1, 0, 2, 1, 0, 3)

        fun validKey(key: String) = key.isABinaryRepresentation() && key.length == 8
    }

    fun crypt(plainText: String, key: String): String {
        return key shiftLeft Integer.valueOf(plainText)
    }

    fun decrypt(cryptedText: String, key: String) {

    }

    private fun generateSubKeys(key: String) {

    }
}