package com.chunglyric.taipeicityzootour.ui.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ReloadImageUiState(
    val timestamp: String = ""
)

class ReloadImageViewModel : ViewModel() {
    private val viewModelStates = MutableStateFlow(ReloadImageUiState())
    val uiState: StateFlow<ReloadImageUiState> = viewModelStates.asStateFlow()

    fun reloadImage() {
        viewModelStates.value = ReloadImageUiState(timestamp = System.currentTimeMillis().toString())
    }
}
