package com.example.gaiaguard.ui.screen.game

import com.example.gaiaguard.data.model.Item

data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 1,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false,
    val showItemDetails: Boolean = false,
    val item: Item = Item()
)
