package com.statecraft.kastoria.model

data class GameState(
    val week: Int = 1,
    val stats: Map<String, Int> = mapOf(
        "economy" to 50,
        "approval" to 55,
        "stability" to 60,
        "military" to 50,
        "diplomacy" to 45,
        "corruption" to 35
    ),
    val budget: Map<String, Int> = mapOf(
        "health" to 20,
        "education" to 20,
        "military" to 25,
        "infrastructure" to 20,
        "reserve" to 15
    ),
    val flags: Set<String> = emptySet(),
    val ministers: List<Minister> = emptyList(),
    val currentNodeId: String = "intro_1",
    val log: List<String> = emptyList()
)

fun GameState.withStatDelta(stat: String, delta: Int): GameState {
    val current = stats[stat] ?: 50
    val updated = (current + delta).coerceIn(0, 100)
    return copy(stats = stats + (stat to updated))
}
