package com.example.gaiaguard.ui.screen.menu

import com.example.gaiaguard.data.model.ObjectiveItem

data class MenuUiState(
    val stringResourceId: Int = 0,
    val imageResourceId: Int = 0,
    val task: List<ObjectiveItem> = listOf()
)
