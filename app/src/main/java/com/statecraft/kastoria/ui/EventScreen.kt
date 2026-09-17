package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.StoryNode

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
            Text(text = node.speaker, style = MaterialTheme.typography.labelLarge, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(text = node.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        choices.forEach { choice ->
            OutlinedButton(
                onClick = { onChoose(choice) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(choice.text)
            }
        }
    }
}
