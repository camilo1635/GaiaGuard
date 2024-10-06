package com.example.gaiaguard.ui.screen.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.gaiaguard.data.model.ObjetiveItem

data class MenuUiState(
    val stringResourceId: Int = 0,
    val imageResourceId: Int = 0,
    val task: List<ObjetiveItem> = listOf()
)
