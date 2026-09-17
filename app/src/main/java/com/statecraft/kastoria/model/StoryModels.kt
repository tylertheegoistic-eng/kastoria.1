package com.statecraft.kastoria.model

import kotlinx.serialization.Serializable

@Serializable
data class StoryContent(
    val nodes: List<StoryNode>
)

/**
 * A single beat of the game. `type` decides which screen renders it:
 *  - "dialogue": a narrative beat with branching choices (visual-novel style)
 *  - "budget":   the budget-allocation dashboard
 *  - "cabinet":  the cabinet management dashboard
 *  - "ending":   a terminal screen, no choices
 */
@Serializable
data class StoryNode(
    val id: String,
    val arc: String,
    val type: String = "dialogue",
    val speaker: String = "",
    val text: String,
    val choices: List<Choice> = emptyList()
)

@Serializable
data class Choice(
    val text: String,
    val requiresFlag: String? = null,
    val forbidsFlag: String? = null,
    val minStat: StatCheck? = null,
    val effects: List<StatEffect> = emptyList(),
    val setFlags: List<String> = emptyList(),
    val next: String
)

@Serializable
data class StatCheck(val stat: String, val value: Int)

@Serializable
data class StatEffect(val stat: String, val delta: Int)
