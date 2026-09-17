package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.Minister
import com.statecraft.kastoria.model.StoryNode

@Composable
fun CabinetScreen(
    node: StoryNode,
    ministers: List<Minister>,
    choices: List<Choice>,
    onChoose: (Choice) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(text = node.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(ministers) { minister ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "${minister.name} — ${minister.portfolio}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Ideology: ${minister.ideology}", style = MaterialTheme.typography.bodySmall)
                        Text(
                            text = "Loyalty: ${minister.loyalty}   Competence: ${minister.competence}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
        choices.forEach { choice ->
            Button(
                onClick = { onChoose(choice) },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text(choice.text)
            }
        }
    }
}
