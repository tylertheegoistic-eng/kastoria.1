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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.StoryNode
import com.statecraft.kastoria.ui.theme.GoldAccent

private val CATEGORIES = listOf("health", "education", "military", "infrastructure", "reserve")

@Composable
fun BudgetScreen(
    node: StoryNode,
    budget: Map<String, Int>,
    choices: List<Choice>,
    onBudgetChange: (Map<String, Int>) -> Unit,
    onConfirm: (Choice) -> Unit
) {
    var localBudget by remember(node.id) { mutableStateOf(budget) }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(text = node.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(18.dp))
        CATEGORIES.forEach { category ->
            val value = localBudget[category] ?: 0
            Text(
                text = "${category.replaceFirstChar { it.uppercase() }}: $value%",
                style = MaterialTheme.typography.titleMedium,
                color = GoldAccent
            )
            Slider(
                value = value.toFloat(),
                onValueChange = { newVal ->
                    localBudget = localBudget + (category to newVal.toInt())
                    onBudgetChange(localBudget)
                },
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = GoldAccent,
                    activeTrackColor = GoldAccent
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        choices.forEach { choice ->
            Button(
                onClick = { onConfirm(choice) },
                colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(choice.text, color = Color(0xFF14110C))
            }
        }
    }
}
