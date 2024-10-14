package com.example.disney

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun CharacterDetailScreen(
    characterId: Int?,
    navController: NavController,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: CharacterDetailViewModel = viewModel(factory = viewModelFactory)
    val character by viewModel.character.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(characterId) {
        characterId?.let { viewModel.loadCharacter(it) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        character?.let { char ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = char.imageUrl,
                    contentDescription = char.name,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = char.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CharacterInfoSection("Films", char.films)
                CharacterInfoSection("TV Shows", char.tvShows)
                CharacterInfoSection("Video Games", char.videoGames)
                CharacterInfoSection("Park Attractions", char.parkAttractions)
                CharacterInfoSection("Allies", char.allies)
                CharacterInfoSection("Enemies", char.enemies)
            }
        }

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CharacterInfoSection(title: String, items: List<String>?) {
    items?.let { nonNullItems ->
        if (nonNullItems.isNotEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            nonNullItems.forEach { item ->
                Text(text = "• $item", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}