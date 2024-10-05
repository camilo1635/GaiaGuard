package com.example.gaiaguard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gaiaguard.data.source.MAX_NO_OF_WORDS
import com.example.gaiaguard.data.source.SCORE_INCREASE
import com.example.gaiaguard.data.source.allWords
import com.example.gaiaguard.ui.screen.game.GameUiState
import com.example.gaiaguard.ui.screen.levels.LevelSelectionUiState
import com.example.gaiaguard.ui.screen.welcome.WelcomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GaiaGuardViewModel: ViewModel() {
    private val _welcomeUiState = MutableStateFlow(WelcomeUiState())
    val welcomeUiState: StateFlow<WelcomeUiState> = _welcomeUiState.asStateFlow()

    private val _levelSelectedUiState = MutableStateFlow(LevelSelectionUiState())
    val levelSelectedUiState: StateFlow<LevelSelectionUiState> = _levelSelectedUiState.asStateFlow()

    // Game UI state
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState: StateFlow<GameUiState> = _gameUiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    // Set of words used in the game
    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    init {
        resetGame()
    }


    fun updateParticipantName(name: String) {
        _welcomeUiState.value = _welcomeUiState.value.copy(participantName = name)
    }

    fun updateLevelSelected(level: Int) {
        _levelSelectedUiState.value = _levelSelectedUiState.value.copy(levelSelected = level)
    }

    /*
     * Re-initializes the game data to restart the game.
     */
    fun resetGame() {
        usedWords.clear()
        _gameUiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    /*
     * Update the user's guess
     */
    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    /*
     * Checks if the user's guess is correct.
     * Increases the score accordingly.
     */
    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // User's guess is correct, increase the score
            // and call updateGameState() to prepare the game for next round
            val updatedScore = _gameUiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // User's guess is wrong, show an error
            _gameUiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    /*
     * Skip to next word
     */
    fun skipWord() {
        updateGameState(_gameUiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    /*
     * Picks a new currentWord and currentScrambledWord and updates UiState according to
     * current game state.
     */
    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS){
            //Last round in the game, update isGameOver to true, don't pick a new word
            _gameUiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else{
            // Normal round in the game
            _gameUiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }


}