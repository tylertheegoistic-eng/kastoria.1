package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.Minister
import com.statecraft.kastoria.model.StoryNode
import com.statecraft.kastoria.ui.theme.CardBackground
import com.statecraft.kastoria.ui.theme.GoldAccent

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
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "${minister.name} — ${minister.portfolio}",
                            style = MaterialTheme.typography.titleMedium,
                            color = GoldAccent
                        )
                        Text(text = "Ideology: ${minister.ideology}", style = MaterialTheme.typography.labelLarge)
                        Text(
                            text = "Loyalty: ${minister.loyalty}   Competence: ${minister.competence}",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
        choices.forEach { choice ->
            Button(
                onClick = { onChoose(choice) },
                colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text(choice.text, color = androidx.compose.ui.graphics.Color(0xFF14110C))
            }
        }
    }
}
