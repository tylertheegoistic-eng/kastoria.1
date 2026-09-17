package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.GameState
import com.statecraft.kastoria.model.StoryNode

@Composable
fun EndingScreen(node: StoryNode, state: GameState, onRestart: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(text = "Week ${state.week} — Journey's End", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = node.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        state.stats.forEach { (k, v) -> Text(text = "${k.replaceFirstChar { it.uppercase() }}: $v") }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRestart) { Text("Play Again") }
    }
}
