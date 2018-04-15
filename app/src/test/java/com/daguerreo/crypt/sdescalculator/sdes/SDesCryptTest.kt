package com.daguerreo.crypt.sdescalculator.sdes

import com.daguerreo.crypt.sdescalculator.ext.binToAscii
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class SDesCryptTest {
    @Test
    fun cryptTest() {
        val sDes = SDesCrypt()
        Assert.assertThat(sDes.crypt("10101011".binToAscii(), "0101011101"), equalTo("01011101".binToAscii()))
        Assert.assertThat(sDes.crypt("C", "1010000010"), equalTo("01000100".binToAscii()))
    }

    @Test
    fun decryptTest() {
        val sDes = SDesCrypt()
        Assert.assertThat(sDes.decrypt("01011101".binToAscii(), "0101011101"), equalTo("10101011".binToAscii()))
        Assert.assertThat(sDes.decrypt("01000100".binToAscii(), "1010000010"), equalTo("C"))
    }
}