package com.clock.berlinclockkata.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clock.berlinclockkata.main.BerlinClockUseCase
import com.clock.berlinclockkata.model.BerlinClockLampState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: BerlinClockUseCase
): ViewModel() {

    private val _berlinClockState = MutableStateFlow(BerlinClockLampState())
    val berlinClockState: StateFlow<BerlinClockLampState> = _berlinClockState.asStateFlow()

    init {
       startConversion()
    }

    private fun startConversion() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.initConversion().collect {
                _berlinClockState.emit(it)
            }
        }
    }
}