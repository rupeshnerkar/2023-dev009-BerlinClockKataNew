package com.clock.berlinclockkata.model


data class BerlinClockLampState(
    val seconds: Lamps = Lamps.OFF,
    val fiveHours: ArrayList<Lamps> = arrayListOf(),
    val oneHour: ArrayList<Lamps> = arrayListOf(),
    val fiveMinutes: ArrayList<Lamps> = arrayListOf(),
    val oneMinute: ArrayList<Lamps> = arrayListOf(),
    val normalTime: String? = null
)
