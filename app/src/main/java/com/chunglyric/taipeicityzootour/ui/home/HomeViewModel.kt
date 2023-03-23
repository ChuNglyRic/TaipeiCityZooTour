package com.chunglyric.taipeicityzootour.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.data.guides.impl.ApiGuidesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface HomeUiStates {
    val isLoading: Boolean

    data class NoData(
        override val isLoading: Boolean
    ) : HomeUiStates

    data class HasData(
        override val isLoading: Boolean
    ) : HomeUiStates
}

private data class HomeViewModelState(
    val isLoading: Boolean = false
) {
    fun toUiState(): HomeUiStates =
        if (isLoading)
            HomeUiStates.NoData(isLoading = isLoading)
        else
            HomeUiStates.HasData(isLoading = isLoading)
}

class HomeViewModel(
    private val guidesRepository: ApiGuidesRepository
) : ViewModel() {
    private val tag = javaClass.simpleName
    private val viewModelStates = MutableStateFlow(HomeViewModelState())

    val uiState = viewModelStates
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelStates.value.toUiState()
        )

    init {
        viewModelStates.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val responseStatus = guidesRepository.getAreaGuide()
            viewModelStates.update {
                when (responseStatus) {
                    is ResponseStatus.Success -> {
                        Log.d(tag, "succeed: ${responseStatus.data}")
                        it.copy(isLoading = false)
                    }
                    is ResponseStatus.Error -> {
                        Log.d(tag, "error: ${responseStatus.response.code()}")
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }
}

