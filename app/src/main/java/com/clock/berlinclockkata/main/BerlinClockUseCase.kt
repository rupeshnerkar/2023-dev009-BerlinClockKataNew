package com.clock.berlinclockkata.main

import androidx.annotation.VisibleForTesting
import com.clock.berlinclockkata.model.BerlinClockLampState


import com.clock.berlinclockkata.model.BerlinClockValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class BerlinClockUseCase @Inject constructor(
    private val berlinClock: BerlinClock,
) {


    fun initConversion(): Flow<BerlinClockLampState> {
        return flow {
            while (true) {
                emit(convert())
                delay(1000L)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun convert(): BerlinClockLampState {
        val time = formatTime(Calendar.getInstance().timeInMillis)
        return toBerlinClockLampState(berlinClock.convert(time),time)
    }

    private fun toBerlinClockLampState(berlinClockValue: BerlinClockValue, time: String) =
        BerlinClockLampState(
            seconds = berlinClockValue.seconds,
            fiveHours = berlinClockValue.fiveHours,
            oneHour = berlinClockValue.oneHour,
            fiveMinutes = berlinClockValue.fiveMinutes,
            oneMinute = berlinClockValue.oneMinute,
            normalTime = time
        )

    private fun formatTime(time:Long): String =
        SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
            .format(Date(time))
}