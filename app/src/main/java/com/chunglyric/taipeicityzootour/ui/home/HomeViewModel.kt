package com.chunglyric.taipeicityzootour.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.data.guides.impl.ApiGuidesRepository
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.model.GuidesCache
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface HomeUiStates {
    val isLoading: Boolean

    data class NoData(
        override val isLoading: Boolean
    ) : HomeUiStates

    data class HasData(
        val guidesCache: GuidesCache,
        override val isLoading: Boolean
    ) : HomeUiStates
}

private data class HomeViewModelState(
    val guidesCache: GuidesCache,
    val isLoading: Boolean = false
) {
    fun toUiState(): HomeUiStates =
        if (guidesCache.areaData == null)
            HomeUiStates.NoData(isLoading = isLoading)
        else
            HomeUiStates.HasData(
                guidesCache = guidesCache,
                isLoading = isLoading
            )
}

class HomeViewModel(
    private val guidesRepository: ApiGuidesRepository,
    private val guidesCache: GuidesCache
) : ViewModel() {
    private val tag = javaClass.simpleName
    private val viewModelStates = MutableStateFlow(HomeViewModelState(guidesCache = guidesCache))

    val uiState = viewModelStates
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelStates.value.toUiState()
        )

    init {
        refreshGuides()
    }

    fun refreshGuides() {
        viewModelStates.update {
            guidesCache.areaData = null
            guidesCache.animalData = null
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            try {
                val areaGuideResponseStatus = guidesRepository.getAreaGuide()

                val count: Int? = when (val animalGuideCountResponseStatus = guidesRepository.getAnimalGuideCount()) {
                    is ResponseStatus.Success -> {
                        animalGuideCountResponseStatus.data.result.count
                    }
                    is ResponseStatus.Error -> {
                        null
                    }
                }

                val animalDataList: List<AnimalGuide.Data>? = when (val animalGuideResponseStatus = guidesRepository.getAnimalGuide(limit = count)) {
                    is ResponseStatus.Success -> {
                        animalGuideResponseStatus.data.result.results
                    }
                    is ResponseStatus.Error -> {
                        null
                    }
                }

                viewModelStates.update {
                    when (areaGuideResponseStatus) {
                        is ResponseStatus.Success -> {
                            guidesCache.areaData = areaGuideResponseStatus.data.result.results
                            guidesCache.animalData = animalDataList
                            it.copy(
                                guidesCache = guidesCache,
                                isLoading = false
                            )
                        }
                        is ResponseStatus.Error -> {
                            Log.d(tag, "error: ${areaGuideResponseStatus.response.code()}")
                            it.copy(isLoading = false)
                        }
                    }
                }
            } catch (exception: Exception) {
                viewModelStates.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            apiGuidesRepository: ApiGuidesRepository,
            guidesCache: GuidesCache
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(apiGuidesRepository, guidesCache) as T
            }
        }
    }
}

