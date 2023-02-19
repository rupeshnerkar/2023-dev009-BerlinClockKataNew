package com.clock.berlinclockkata.main

import com.clock.berlinclockkata.model.BerlinClockValue
import com.clock.berlinclockkata.model.Lamps
import javax.inject.Inject


class BerlinClock @Inject constructor() {

    fun convert(time: String): BerlinClockValue {
        val splitTime = time.split(":")
        val seconds = toSeconds(splitTime[2].toInt())
        val fiveHours = toFiveHoursLamps(splitTime[0].toInt())
        val oneHour = toOneHoursLamps(splitTime[0].toInt())
        val fiveMinutes = toFiveMinutesLamps(splitTime[1].toInt())
        val oneMinute = toOneMinutesLamps(splitTime[1].toInt())

        return BerlinClockValue(seconds, fiveHours, oneHour, fiveMinutes, oneMinute)
    }

    private fun toOneMinutesLamps(minutes: Int): ArrayList<Lamps> {
        val oneMinuteLamps = minutes % 5
        val list = arrayListOf<Lamps>()
        for (i in 1..4) {
            list.add(
                if (i <= oneMinuteLamps)
                    Lamps.YELLOW
                else Lamps.OFF
            )
        }
        return list
    }

    private fun toFiveMinutesLamps(minutes: Int): ArrayList<Lamps> {
        val fiveMinutesLamps = minutes / 5
        val list = arrayListOf<Lamps>()
        for (i in 1..11) {
            list.add(
                if (i <= fiveMinutesLamps) {
                    if(i % 3 == 0)
                        Lamps.RED
                    else Lamps.YELLOW
                }
                else Lamps.OFF
            )
        }
        return list
    }

    private fun toOneHoursLamps(hours: Int): ArrayList<Lamps> {
        return convertHours(hours % 5)
    }

    private fun toFiveHoursLamps(hours: Int): ArrayList<Lamps> {
        return convertHours(hours / 5)
    }


    private fun convertHours(hourLamps: Int): ArrayList<Lamps> {
        val list = arrayListOf<Lamps>()
        for (i in 1..4) {
            list.add(
                if (i <= hourLamps)
                    Lamps.RED
                else Lamps.OFF
            )
        }
        return list
    }

    private fun toSeconds(seconds: Int): Lamps {
        return if(seconds % 2 == 0) Lamps.YELLOW else Lamps.OFF
    }
}