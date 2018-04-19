package com.daguerreo.crypt.sdescalculator.sdes

import com.daguerreo.crypt.sdescalculator.ext.binToAscii
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class SDesCryptTest {
    class StubSdesStringResource : SdesStringResource {
        override val plainTextOrEncryptedText = ""
        override val binRepresentation = ""
        override val orInDecimal = ""
        override val encrypted = ""
        override val decrypted = ""
        override val text = ""
    }

    @Test
    fun cryptTest() {
        val sDes = SDesCrypt(StubSdesStringResource())
        Assert.assertThat(sDes.encrypt("10101011".binToAscii(), "0101011101").result, equalTo("01011101".binToAscii()))
        Assert.assertThat(sDes.encrypt("C", "1010000010").result, equalTo("01000100".binToAscii()))
    }

    @Test
    fun decryptTest() {
        val sDes = SDesCrypt(StubSdesStringResource())
        Assert.assertThat(sDes.decrypt("01011101".binToAscii(), "0101011101").result, equalTo("10101011".binToAscii()))
        Assert.assertThat(sDes.decrypt("01000100".binToAscii(), "1010000010").result, equalTo("C"))
    }
}