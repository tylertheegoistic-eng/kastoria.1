package com.statecraft.kastoria.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.statecraft.kastoria.engine.ContentLoader
import com.statecraft.kastoria.engine.GameEngine
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.GameState
import com.statecraft.kastoria.model.StoryNode
import com.statecraft.kastoria.model.withStatDelta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val story = ContentLoader.loadStory(application)
    private val engine = GameEngine(story)
    private val ministerRoster = ContentLoader.loadMinisters(application).ministers

    private val _state = MutableStateFlow(GameState(ministers = ministerRoster))
    val state: StateFlow<GameState> = _state

    fun currentNode(): StoryNode? = engine.currentNode(_state.value)
    fun availableChoices(): List<Choice> = engine.availableChoices(_state.value)

    /** Used by dialogue, cabinet, and ending-triggering choices. */
    fun choose(choice: Choice) {
        var newState = engine.applyChoice(_state.value, choice)
        if (newState.currentNodeId == "AUTO_ENDING") {
            newState = newState.copy(currentNodeId = resolveEnding(newState))
        }
        _state.value = newState
    }

    /** Live-updates the budget sliders before the player confirms. */
    fun updateBudget(newBudget: Map<String, Int>) {
        _state.value = _state.value.copy(budget = newBudget)
    }

    /** Confirms the budget node: converts allocations into stat effects, then advances. */
    fun confirmBudget(choice: Choice) {
        val b = _state.value.budget
        var s = _state.value
        val econDelta = (((b["infrastructure"] ?: 20) - 20) / 3) + (((b["reserve"] ?: 15) - 15) / 4)
        val approvalDelta = (((b["health"] ?: 20) - 20) / 4) + (((b["education"] ?: 20) - 20) / 4)
        val militaryDelta = ((b["military"] ?: 20) - 20) / 3
        s = s.withStatDelta("economy", econDelta)
            .withStatDelta("approval", approvalDelta)
            .withStatDelta("military", militaryDelta)
        s = engine.applyChoice(s, choice)
        _state.value = s.copy(week = s.week + 1)
    }

    fun restart() {
        _state.value = GameState(ministers = ministerRoster)
    }

    /** Decides which of the four endings fits the player's final national indicators. */
    private fun resolveEnding(s: GameState): String {
        val approval = s.stats["approval"] ?: 0
        val stability = s.stats["stability"] ?: 0
        val economy = s.stats["economy"] ?: 0
        return when {
            s.flags.contains("authoritarian") && stability >= 50 -> "ending_authoritarian_grip"
            stability < 30 || economy < 20 -> "ending_collapse"
            approval >= 55 -> "ending_reelected"
            else -> "ending_ousted"
        }
    }
}
