package com.example.disney

import DisneyApiService
import DisneyCharacter
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CharacterListViewModel(private val disneyApiService: DisneyApiService) : ViewModel() {
    private val _characters = MutableStateFlow<List<DisneyCharacter>>(emptyList())
    val characters: StateFlow<List<DisneyCharacter>> = _characters.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentPage = 1
    private var hasMorePages = true

    fun loadCharacters() {
        if (_isLoading.value || !hasMorePages) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = disneyApiService.getCharacters(currentPage)
                _characters.value = _characters.value + response.data
                currentPage++
                hasMorePages = response.nextPage != null
            } catch (e: Exception) {
                handleError(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun handleError(e: Exception) {
        _errorMessage.value = when (e) {
            is IOException -> "Network error. Please check your internet connection."
            is HttpException -> "Server error. Please try again later."
            else -> "An unexpected error occurred: ${e.message}"
        }
    }

    fun resetErrorState() {
        _errorMessage.value = null
    }

    fun refreshCharacters() {
        _characters.value = emptyList()
        currentPage = 1
        hasMorePages = true
        loadCharacters()
    }
}