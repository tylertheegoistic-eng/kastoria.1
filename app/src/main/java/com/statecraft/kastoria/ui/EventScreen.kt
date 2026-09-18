package com.statecraft.kastoria.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.StoryNode
import com.statecraft.kastoria.ui.theme.CardBackground
import com.statecraft.kastoria.ui.theme.GoldAccent

@Composable
fun EventScreen(
    node: StoryNode,
    choices: List<Choice>,
    onChoose: (Choice) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (node.speaker.isNotBlank()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(GoldAccent),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = node.speaker.trim().firstOrNull()?.uppercase() ?: "?",
                        color = Color(0xFF14110C),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = node.speaker,
                    style = MaterialTheme.typography.labelLarge,
                    fontStyle = FontStyle.Italic,
                    color = GoldAccent
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = node.text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        choices.forEach { choice ->
            OutlinedButton(
                onClick = { onChoose(choice) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldAccent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Text(choice.text, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
