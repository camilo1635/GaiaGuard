package com.example.gaiaguard

import androidx.lifecycle.ViewModel
import com.example.gaiaguard.ui.screen.levels.LevelSelectionUiState
import com.example.gaiaguard.ui.screen.welcome.WelcomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GaiaGuardViewModel: ViewModel() {
    private val _welcomeUiState = MutableStateFlow(WelcomeUiState())
    val welcomeUiState: StateFlow<WelcomeUiState> = _welcomeUiState.asStateFlow()

    private val _levelSelectedUiState = MutableStateFlow(LevelSelectionUiState())
    val levelSelectedUiState: StateFlow<LevelSelectionUiState> = _levelSelectedUiState.asStateFlow()



    fun updateParticipantName(name: String) {
        _welcomeUiState.value = _welcomeUiState.value.copy(participantName = name)
    }

    fun updateLevelSelected(level: Int) {
        _levelSelectedUiState.value = _levelSelectedUiState.value.copy(levelSelected = level)
    }


}