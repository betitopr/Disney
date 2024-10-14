package com.example.disney

import DisneyCharacter

data class CharacterResponse(
    val data: List<DisneyCharacter>,
    val nextPage: String?
)