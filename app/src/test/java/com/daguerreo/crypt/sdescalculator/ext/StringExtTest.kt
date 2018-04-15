package com.daguerreo.crypt.sdescalculator.ext

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class StringExtTest {
    @Test
    fun stringIsABinaryRepresentationTest() {
        Assert.assertThat("0110101010010101010100".isABinaryRepresentation(), equalTo(true))
        Assert.assertThat("000".isABinaryRepresentation(), equalTo(true))
        Assert.assertThat("111111111".isABinaryRepresentation(), equalTo(true))
        Assert.assertThat("".isABinaryRepresentation(), equalTo(false))
        Assert.assertThat("111010101102".isABinaryRepresentation(), equalTo(false))
        Assert.assertThat("sdfsdffds".isABinaryRepresentation(), equalTo(false))
    }

    @Test
    fun intAbsModTest() {
        Assert.assertThat(-1 absMod 5, equalTo(4))
        Assert.assertThat(1 absMod 5, equalTo(1))
        Assert.assertThat(5 absMod 5, equalTo(0))
        Assert.assertThat(-5 absMod 5, equalTo(0))
        Assert.assertThat(-6 absMod 5, equalTo(4))
        Assert.assertThat(-4 absMod 5, equalTo(1))
        Assert.assertThat(4 absMod 5, equalTo(4))
    }

    @Test
    fun stringShiftLeftTest() {
        val expected = arrayOf("01000", "10000", "00001", "00010", "00100",
                               "01000", "10000", "00001", "00010", "00100")
        for (i in 1..10) {
            Assert.assertThat("00100" shiftLeft i, equalTo(expected[i-1]))
        }

        val expected2 = arrayOf("1010100", "0101001", "1010010", "0100101", "1001010", "0010101", "0101010",
                                "1010100", "0101001", "1010010", "0100101", "1001010", "0010101", "0101010")
        for (i in 1..14) {
            Assert.assertThat("0101010" shiftLeft i, equalTo(expected2[i-1]))
        }
    }

    @Test
    fun stringShiftRightTest() {
        val expected = arrayOf("00010", "00001", "10000", "01000", "00100",
                               "00010", "00001", "10000", "01000", "00100")
        for (i in 1..10) {
            Assert.assertThat("00100" shiftRight i, equalTo(expected[i-1]))
        }

        val expected2 = arrayOf("0010101", "1001010", "0100101", "1010010", "0101001", "1010100", "0101010",
                                "0010101", "1001010", "0100101", "1010010", "0101001", "1010100", "0101010")
        for (i in 1..14) {
            Assert.assertThat("0101010" shiftRight i, equalTo(expected2[i-1]))
        }
    }

    @Test
    fun stringPermutTest() {
        val p10 = arrayOf(3, 5, 2, 7, 4, 10, 1, 9, 8, 6)
        val p8rd = arrayOf(6, 3, 7, 4, 8, 5, 10, 9)
        val p5 = arrayOf(5, 3, 4, 1, 2)
        val p5e = arrayOf(1, 2, 3, 4, 5)
        val p4 = arrayOf(2, 4, 3, 1)
        val p8r = arrayOf(4, 1, 2, 3, 2, 3, 4, 1)

        val testsP4 = arrayOf(Pair("0010", "0010"), Pair("1010", "0011"), Pair("0111", "1110"), Pair("0000", "0000"))
        testsP4.forEach { Assert.assertThat(it.first.permut(p4), equalTo(it.second)) }

        val testsP8r = arrayOf(Pair("0010", "00010100"), Pair("1010", "01010101"), Pair("0111", "10111110"), Pair("0000", "00000000"))
        testsP8r.forEach { Assert.assertThat(it.first.permut(p8r), equalTo(it.second)) }

        val testsP5e = arrayOf(Pair("00100", "00100"), Pair("10100", "10100"), Pair("01111", "01111"), Pair("00000", "00000"))
        testsP5e.forEach { Assert.assertThat(it.first.permut(p5e), equalTo(it.second)) }

        val testsP5 = arrayOf(Pair("00100", "01000"), Pair("10100", "01010"), Pair("01111", "11101"), Pair("00000", "00000"))
        testsP5.forEach { Assert.assertThat(it.first.permut(p5), equalTo(it.second)) }

        val testsP8rd = arrayOf(Pair("0010000100", "01001000"), Pair("1010111010", "11100101"),
                                Pair("0111010001", "11010010"), Pair("0000000000", "00000000"))
        testsP8rd.forEach { Assert.assertThat(it.first.permut(p8rd), equalTo(it.second)) }

        val testsP10 = arrayOf(Pair("0010000100", "1000000010"), Pair("1010111010", "1101001101"),
                               Pair("0111010001", "1010110001"), Pair("0000000000", "0000000000"))
        testsP10.forEach { Assert.assertThat(it.first.permut(p10), equalTo(it.second)) }

        Assert.assertThat("0101010".permut(arrayOf()), equalTo(""))
    }

    @Test
    fun intToBinTest() {
        val bins = arrayOf(Pair(255, "11111111"), Pair(256, "100000000"), Pair(0, "0"), Pair(1, "1"),
                           Pair(2, "10"), Pair(3, "11"), Pair(4, "100"), Pair(5, "101"),
                           Pair(6, "110"), Pair(7, "111"), Pair(8, "1000"), Pair(9, "1001"),
                           Pair(10, "1010"), Pair(11, "1011"), Pair(12, "1100"), Pair(13, "1101"),
                           Pair(14, "1110"), Pair(15, "1111"), Pair(16, "10000"), Pair(32, "100000"),
                           Pair(64, "1000000"), Pair(128, "10000000"), Pair(37, "100101"), Pair(127, "1111111"),
                           Pair(77, "1001101"), Pair(169, "10101001"), Pair(202, "11001010"), Pair(243, "11110011"))
        bins.forEach { Assert.assertThat(it.first.toBin(), equalTo(it.second)) }

        val binsMinLength = arrayOf(Pair(255, "11111111"), Pair(256, "100000000"), Pair(0, "00000000"), Pair(1, "00000001"),
                                    Pair(2, "00000010"), Pair(3, "00000011"), Pair(4, "00000100"), Pair(5, "00000101"),
                                    Pair(6, "00000110"), Pair(7, "00000111"), Pair(8, "00001000"), Pair(9, "00001001"),
                                    Pair(10, "00001010"), Pair(11, "00001011"), Pair(12, "00001100"), Pair(13, "00001101"),
                                    Pair(14, "00001110"), Pair(15, "00001111"), Pair(16, "00010000"), Pair(32, "00100000"),
                                    Pair(64, "01000000"), Pair(128, "10000000"), Pair(37, "00100101"), Pair(127, "01111111"),
                                    Pair(77, "01001101"), Pair(169, "10101001"), Pair(202, "11001010"), Pair(243, "11110011"))
        binsMinLength.forEach { Assert.assertThat(it.first.toBin(8), equalTo(it.second)) }
    }

    @Test
    fun charToBinTest() {
        val bins = arrayOf(Pair(255, "11111111"), Pair(256, "100000000"), Pair(0, "0"), Pair(1, "1"),
                           Pair(2, "10"), Pair(3, "11"), Pair(4, "100"), Pair(5, "101"),
                           Pair(6, "110"), Pair(7, "111"), Pair(8, "1000"), Pair(9, "1001"),
                           Pair(10, "1010"), Pair(11, "1011"), Pair(12, "1100"), Pair(13, "1101"),
                           Pair(14, "1110"), Pair(15, "1111"), Pair(16, "10000"), Pair(32, "100000"),
                           Pair(64, "1000000"), Pair(128, "10000000"), Pair(37, "100101"), Pair(127, "1111111"),
                           Pair(77, "1001101"), Pair(169, "10101001"), Pair(202, "11001010"), Pair(243, "11110011"))
        bins.forEach { Assert.assertThat(it.first.toChar().toBin(), equalTo(it.second)) }

        val binsMinLength = arrayOf(Pair(255, "11111111"), Pair(256, "100000000"), Pair(0, "00000000"), Pair(1, "00000001"),
                Pair(2, "00000010"), Pair(3, "00000011"), Pair(4, "00000100"), Pair(5, "00000101"),
                Pair(6, "00000110"), Pair(7, "00000111"), Pair(8, "00001000"), Pair(9, "00001001"),
                Pair(10, "00001010"), Pair(11, "00001011"), Pair(12, "00001100"), Pair(13, "00001101"),
                Pair(14, "00001110"), Pair(15, "00001111"), Pair(16, "00010000"), Pair(32, "00100000"),
                Pair(64, "01000000"), Pair(128, "10000000"), Pair(37, "00100101"), Pair(127, "01111111"),
                Pair(77, "01001101"), Pair(169, "10101001"), Pair(202, "11001010"), Pair(243, "11110011"))
        binsMinLength.forEach { Assert.assertThat(it.first.toChar().toBin(8), equalTo(it.second)) }
    }

    @Test
    fun stringBinToByteTest() {
        val bins = arrayOf(Pair(255, "11111111"), Pair(256, "100000000"), Pair(0, "0"), Pair(1, "1"),
                Pair(2, "10"), Pair(3, "11"), Pair(4, "100"), Pair(5, "101"),
                Pair(6, "110"), Pair(7, "111"), Pair(8, "1000"), Pair(9, "1001"),
                Pair(10, "1010"), Pair(11, "1011"), Pair(12, "1100"), Pair(13, "1101"),
                Pair(14, "1110"), Pair(15, "1111"), Pair(16, "10000"), Pair(32, "100000"),
                Pair(64, "1000000"), Pair(128, "10000000"), Pair(37, "100101"), Pair(127, "1111111"),
                Pair(77, "1001101"), Pair(169, "10101001"), Pair(202, "11001010"), Pair(243, "11110011"))
        bins.forEach { Assert.assertThat(it.second.binToByte(), equalTo(it.first)) }
    }

    @Test
    fun stringPairSplitTest() {
        val tests = arrayOf(Triple("01", "0", "1"), Triple("010", "0", "10"), Triple("0110", "01", "10"),
                            Triple("abc", "a", "bc"), Triple("abcd", "ab", "cd"), Triple("abab", "ab", "ab"))
        tests.forEach { Assert.assertThat(it.first.pairSplit(), equalTo(Pair(it.second, it.third))) }
    }

    @Test
    fun stringBitToAsciiTest() {
        val tests = arrayOf(Pair("1000001", "A"), Pair("01000011", "C"))
        tests.forEach { Assert.assertThat(it.first.binToAscii(), equalTo(it.second)) }
    }
}