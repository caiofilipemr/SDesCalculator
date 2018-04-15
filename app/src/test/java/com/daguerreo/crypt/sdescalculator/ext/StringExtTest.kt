package com.daguerreo.crypt.sdescalculator.ext

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class StringExtTest {
    @Test
    fun intToBinTest() {
        Assert.assertThat(255.toBin(), equalTo("11111111"))
        Assert.assertThat(2.toBin(), equalTo("10"))
        Assert.assertThat(1.toBin(), equalTo("1"))
        Assert.assertThat(256.toBin(), equalTo("100000000"))
        Assert.assertThat(0.toBin(), equalTo("0"))
    }
}