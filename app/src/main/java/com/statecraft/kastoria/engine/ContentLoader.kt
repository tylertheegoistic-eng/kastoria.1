package com.statecraft.kastoria.engine

import android.content.Context
import com.statecraft.kastoria.model.MinisterRoster
import com.statecraft.kastoria.model.StoryContent
import kotlinx.serialization.json.Json

object ContentLoader {
    private val json = Json { ignoreUnknownKeys = true }

    fun loadStory(context: Context): StoryContent {
        val text = context.assets.open("story_arcs.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(StoryContent.serializer(), text)
    }

    fun loadMinisters(context: Context): MinisterRoster {
        val text = context.assets.open("ministers.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(MinisterRoster.serializer(), text)
    }
}
