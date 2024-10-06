package com.example.gaiaguard

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gaiaguard.data.model.Item
import com.example.gaiaguard.data.repository.ObjectivesRepository
import com.example.gaiaguard.data.source.MAX_NO_OF_WORDS
import com.example.gaiaguard.data.source.ObjectivesLocalDataSource
import com.example.gaiaguard.data.source.ObjectivesRemoteDataSource
import com.example.gaiaguard.data.source.SCORE_INCREASE
import com.example.gaiaguard.data.source.allWords
import com.example.gaiaguard.ui.screen.game.GameUiState
import com.example.gaiaguard.ui.screen.levels.LevelSelectionUiState
import com.example.gaiaguard.ui.screen.menu.MenuUiState
import com.example.gaiaguard.ui.screen.welcome.WelcomeUiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GaiaGuardViewModel: ViewModel() {

    private var items: List<Item> = mutableListOf()
    private lateinit var objectivesRepository: ObjectivesRepository

    private val _welcomeUiState = MutableStateFlow(WelcomeUiState())
    val welcomeUiState: StateFlow<WelcomeUiState> = _welcomeUiState.asStateFlow()

    private val _objetiveSelectionUiState = MutableStateFlow(MenuUiState())
    val objetiveSelectionUiState: StateFlow<MenuUiState> = _objetiveSelectionUiState.asStateFlow()

    var levelSelected by mutableStateOf(1)
        private set

    var objectiveSelected by mutableStateOf(1)
        private set

    // Game UI state
    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState: StateFlow<GameUiState> = _gameUiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    // Set of words used in the game
    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    init {
        objectivesRepository = ObjectivesRepository(
            ObjectivesLocalDataSource(),
            ObjectivesRemoteDataSource(FirebaseFirestore.getInstance()))

        //resetGame()
    }


    fun updateParticipantName(name: String) {
        _welcomeUiState.value = _welcomeUiState.value.copy(participantName = name)
    }

    fun updateObjectiveSelected(objectiveId: Int) {
        objectiveSelected = objectiveId
    }

    fun updateLevelSelected(level: Int) {
        levelSelected = level
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
        if (usedWords.size == items.size){
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
        if (!items.isEmpty()) {
            val allWords = items.map { it.palabra }
            // Continue picking up a new random word until you get one that hasn't been used before
            currentWord = allWords.random().toString()
            return if (usedWords.contains(currentWord)) {
                pickRandomWordAndShuffle()
            } else {
                usedWords.add(currentWord)
                shuffleCurrentWord(currentWord)
            }
        } else {

            currentWord = "Sin palabras disponibles"
            return currentWord
        }
    }

    fun getObjectives() {
        viewModelScope.launch {
            objectivesRepository.getObjectives().collect {
                objectives ->
                    _objetiveSelectionUiState.update { it.copy(task = objectives) }
            }
        }
    }

    fun getObjective(objectiveId: Int): String {
        return _objetiveSelectionUiState.value.task.find { it.numeroODS == objectiveId }?.nombre ?: ""
    }

    fun getItemsFromObjective(objectiveId: String) {
        Log.d("GaiaGuardViewModel", "getItemsFromObjective called with objectiveId: $objectiveId")
        viewModelScope.launch {
            objectivesRepository.getItemsFromObjective(objectiveId).collect {
                Log.d("GaiaGuardViewModel", "Items from objective $objectiveId: $it")
                items = it
                pickRandomWordAndShuffle()
            }
        }
    }
}