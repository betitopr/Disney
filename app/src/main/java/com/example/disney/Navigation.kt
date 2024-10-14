package com.example.disney

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object CharacterList : Screen("characterList")
    object CharacterDetail : Screen("characterDetail/{characterId}") {
        fun createRoute(characterId: Int) = "characterDetail/$characterId"
    }
}

@Composable
fun DisneyNavigation(viewModelFactory: ViewModelProvider.Factory) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.CharacterList.route) {
        composable(Screen.CharacterList.route) {
            CharacterListScreen(navController, viewModelFactory)
        }
        composable(
            Screen.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId")
            CharacterDetailScreen(characterId, navController, viewModelFactory)
        }
    }
}