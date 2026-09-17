package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.Choice
import com.statecraft.kastoria.model.StoryNode

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
        Spacer(modifier = Modifier.height(16.dp))
        CATEGORIES.forEach { category ->
            val value = localBudget[category] ?: 0
            Text(text = "${category.replaceFirstChar { it.uppercase() }}: $value%")
            Slider(
                value = value.toFloat(),
                onValueChange = { newVal ->
                    localBudget = localBudget + (category to newVal.toInt())
                    onBudgetChange(localBudget)
                },
                valueRange = 0f..100f
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        choices.forEach { choice ->
            Button(
                onClick = { onConfirm(choice) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(choice.text)
            }
        }
    }
}
