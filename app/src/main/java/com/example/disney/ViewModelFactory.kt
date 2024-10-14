package com.example.disney

import DisneyApiService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val disneyApiService: DisneyApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CharacterListViewModel::class.java) ->
                CharacterListViewModel(disneyApiService) as T
            modelClass.isAssignableFrom(CharacterDetailViewModel::class.java) ->
                CharacterDetailViewModel(disneyApiService) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}