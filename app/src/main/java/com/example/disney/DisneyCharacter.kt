data class DisneyCharacter(
    val _id: Int,
    val name: String,
    val imageUrl: String,
    val films: List<String> = emptyList(),
    val tvShows: List<String> = emptyList(),
    val videoGames: List<String> = emptyList(),
    val parkAttractions: List<String> = emptyList(),
    val allies: List<String> = emptyList(),
    val enemies: List<String> = emptyList()
)
