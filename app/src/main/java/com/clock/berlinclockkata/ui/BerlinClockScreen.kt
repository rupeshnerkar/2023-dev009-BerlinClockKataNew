package com.clock.berlinclockkata.ui


import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clock.berlinclockkata.model.BerlinClockLampState
import com.clock.berlinclockkata.model.Lamps
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BerlinClockScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val clockLampState by viewModel.berlinClockState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SecondsLampView(modifier, clockLampState.seconds)
        HourLampsView(modifier, clockLampState)
        MinuteLampsView(modifier, clockLampState)
        DigitalTimeView(modifier, clockLampState.normalTime)
    }
}

@Composable
fun SecondsLampView(modifier: Modifier, seconds: Lamps) {
    Box(
        modifier = modifier
            .testTag("secondsLamp")
            .padding(0.dp, 8.dp, 0.dp, 8.dp)
            .size(50.dp)
            .clip(CircleShape)
            .border(2.dp, Color.DarkGray, CircleShape)
            .background(
                Color(parseColor(seconds.color))
            )
    )
}

@Composable
fun MinuteLampsView(modifier: Modifier, clockLampState: BerlinClockLampState) {
    ElevenLampsRowView(modifier, clockLampState.fiveMinutes)
    FourHoursLampsRow(modifier, clockLampState.oneMinute, "oneMinute")
}

@Composable
fun ElevenLampsRowView(modifier: Modifier, list: ArrayList<Lamps>) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(11) {
            ElevenLampsView(list[it], modifier, "fiveMinutes$it")
        }
    }
}

@Composable
fun ElevenLampsView(lamp: Lamps, modifier: Modifier, tag: String) {
    Column(
        modifier = modifier.padding(0.dp, 0.dp, 2.dp, 0.dp)
    ) {
        drawRectangle(modifier, tag, lamp, 30)
    }
}

@Composable
fun HourLampsView(modifier: Modifier, clockLampState: BerlinClockLampState) {
    FourHoursLampsRow(modifier, clockLampState.fiveHours, "fiveHourLamp")
    FourHoursLampsRow(modifier, clockLampState.oneHour, "oneHourLamp")
}

@Composable
fun FourHoursLampsRow(modifier: Modifier, list: ArrayList<Lamps>, tag: String) {
    if (list.size == 0) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) {
            LampsView(list[it], modifier, "$tag$it")
        }
    }

}

@Composable
fun LampsView(lamp: Lamps, modifier: Modifier, tag: String) {
    Column(
        modifier = modifier
            .padding(0.dp, 0.dp, 8.dp, 0.dp)
    ) {
        drawRectangle(modifier, tag, lamp, 80)
    }
}

@Composable
fun DigitalTimeView(modifier: Modifier, normalTime: String?) {
    Text(
        modifier = modifier.testTag("normalTime"),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        text = normalTime ?: ""
    )
}



@Composable
private fun drawRectangle(
    modifier: Modifier,
    tag: String,
    lamp: Lamps,
    width: Int,
    height: Int = 50
) {
    Box(
        modifier = modifier
            .testTag(tag)
            .padding(0.dp, 8.dp, 0.dp, 8.dp)
            .size(width.dp, height.dp)
            .clip(RectangleShape)
            .border(2.dp, Color.DarkGray, RectangleShape)
            .background(
                Color(parseColor(lamp.color))
            )
    )
}
