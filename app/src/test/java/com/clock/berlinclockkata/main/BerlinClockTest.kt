package com.berlin.kata.domain


import com.clock.berlinclockkata.main.BerlinClock
import com.clock.berlinclockkata.model.Lamps
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class BerlinClockTest {

    private lateinit var berlinClock: BerlinClock

    @Before
    fun setUp() {
        berlinClock = BerlinClock()
    }

    @Test
    fun `test seconds lamp for even number`() {
        val time = "15:23:12"

        val converted = berlinClock.convert(time)

        Assert.assertEquals(Lamps.YELLOW, converted.seconds)
    }

    @Test
    fun `test seconds lamp for odd number`() {
        val time = "15:23:15"

        val converted = berlinClock.convert(time)

        Assert.assertEquals(Lamps.OFF, converted.seconds)
    }

    @Test
    fun `test 5 hour lamps with full red`() {
        val time = "21:23:12"
        val expected = arrayListOf(Lamps.RED,Lamps.RED,Lamps.RED,Lamps.RED)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveHours)
    }

    @Test
    fun `test 5 hour lamps with both red and off`() {
        val time = "15:23:12"
        val expected = arrayListOf(Lamps.RED,Lamps.RED,Lamps.RED,Lamps.OFF)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveHours)
    }

    @Test
    fun `test 5 hour lamps with full off`() {
        val time = "00:23:12"
        val expected = arrayListOf(Lamps.OFF,Lamps.OFF,Lamps.OFF,Lamps.OFF)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveHours)
    }

    @Test
    fun `test 1 hour lamps with full red`() {
        val time = "19:23:12"
        val expected = arrayListOf(Lamps.RED,Lamps.RED,Lamps.RED,Lamps.RED)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.oneHour)
    }

    @Test
    fun `test 1 hour lamps with full off`() {
        val time = "15:23:12"
        val expected = arrayListOf(Lamps.OFF,Lamps.OFF,Lamps.OFF,Lamps.OFF)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.oneHour)
    }

    @Test
    fun `test 1 hour lamps with both red and off`() {
        val time = "17:23:12"
        val expected = arrayListOf(Lamps.RED,Lamps.RED,Lamps.OFF,Lamps.OFF)

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.oneHour)
    }

    @Test
    fun `test 5 minutes lamps with all lamps illuminated`() {
        val time = "17:56:12"
        val expected = arrayListOf(
            Lamps.YELLOW,Lamps.YELLOW,Lamps.RED,Lamps.YELLOW,
            Lamps.YELLOW,Lamps.RED,Lamps.YELLOW,Lamps.YELLOW,
            Lamps.RED,Lamps.YELLOW,Lamps.YELLOW
        )

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveMinutes)
    }

    @Test
    fun `test 5 minutes lamps with all lamps are off`() {
        val time = "17:00:12"
        val expected = arrayListOf(
            Lamps.OFF,Lamps.OFF,Lamps.OFF,Lamps.OFF,
            Lamps.OFF,Lamps.OFF,Lamps.OFF,Lamps.OFF,
            Lamps.OFF,Lamps.OFF,Lamps.OFF
        )

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveMinutes)
    }

    @Test
    fun `test 5 minutes lamps with partial lamps illuminated`() {
        val time = "17:32:12"
        val expected = arrayListOf(
            Lamps.YELLOW,Lamps.YELLOW,Lamps.RED,Lamps.YELLOW,
            Lamps.YELLOW,Lamps.RED,Lamps.OFF,Lamps.OFF,
            Lamps.OFF,Lamps.OFF,Lamps.OFF
        )

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.fiveMinutes)
    }

    @Test
    fun `test 1 minute lamps with all lamps on`() {
        val time = "17:34:12"
        val expected = arrayListOf(
            Lamps.YELLOW,Lamps.YELLOW,Lamps.YELLOW,Lamps.YELLOW,
        )

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.oneMinute)
    }

    @Test
    fun `test 1 minute lamps with all lamps off`() {
        val time = "17:35:12"
        val expected = arrayListOf(
            Lamps.OFF,Lamps.OFF,Lamps.OFF,Lamps.OFF,
        )

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.oneMinute)
    }

    @Test
    fun `test 1 minute lamps with partial lamps on`() {
        val time = "17:37:12"
        val expected = arrayListOf(
            Lamps.YELLOW,Lamps.YELLOW,Lamps.OFF,Lamps.OFF,
        )

        val actual = berlinClock.convert(time)


        Assert.assertEquals(expected, actual.oneMinute)
    }
}