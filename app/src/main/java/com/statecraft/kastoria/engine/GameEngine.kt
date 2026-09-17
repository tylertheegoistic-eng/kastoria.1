package com.statecraft.kastoria.engine

import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.GameState
import com.statecraft.kastoria.model.StoryContent
import com.statecraft.kastoria.model.StoryNode
import com.statecraft.kastoria.model.withStatDelta

class GameEngine(private val story: StoryContent) {

    private val nodesById = story.nodes.associateBy { it.id }

    fun currentNode(state: GameState): StoryNode? = nodesById[state.currentNodeId]

    /** Filters a node's choices down to the ones the player is currently allowed to pick. */
    fun availableChoices(state: GameState): List<Choice> {
        val node = currentNode(state) ?: return emptyList()
        return node.choices.filter { choice ->
            val flagOk = choice.requiresFlag?.let { state.flags.contains(it) } ?: true
            val forbidOk = choice.forbidsFlag?.let { !state.flags.contains(it) } ?: true
            val statOk = choice.minStat?.let { (state.stats[it.stat] ?: 0) >= it.value } ?: true
            flagOk && forbidOk && statOk
        }
    }

    fun applyChoice(state: GameState, choice: Choice): GameState {
        var newState = state
        choice.effects.forEach { effect ->
            newState = newState.withStatDelta(effect.stat, effect.delta)
        }
        newState = newState.copy(
            flags = newState.flags + choice.setFlags,
            currentNodeId = choice.next,
            log = newState.log + choice.text
        )
        return newState
    }
}
