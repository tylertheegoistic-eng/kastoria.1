package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.GameState
import com.statecraft.kastoria.model.StoryNode
import com.statecraft.kastoria.ui.theme.CardBackground
import com.statecraft.kastoria.ui.theme.GoldAccent

@Composable
fun EndingScreen(node: StoryNode, state: GameState, onRestart: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            text = "Week ${state.week} — Journey's End",
            style = MaterialTheme.typography.headlineMedium,
            color = GoldAccent
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = node.text, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(18.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))
        state.stats.forEach { (k, v) ->
            Text(text = "${k.replaceFirstChar { it.uppercase() }}: $v", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRestart,
            colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Play Again", color = Color(0xFF14110C))
        }
    }
}
