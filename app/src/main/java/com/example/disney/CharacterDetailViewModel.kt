package com.example.disney

import DisneyApiService
import DisneyCharacter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val disneyApiService: DisneyApiService
) : ViewModel() {
    private val _character = MutableStateFlow<DisneyCharacter?>(null)
    val character: StateFlow<DisneyCharacter?> = _character

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            try {
                _character.value = disneyApiService.getCharacter(id)
            } catch (e: Exception) {
                _errorMessage.value = "Error loading character: ${e.message}"
            }
        }
    }
}