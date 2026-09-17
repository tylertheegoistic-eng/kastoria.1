package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.GameState

@Composable
fun DashboardDialog(state: GameState, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        },
        title = { Text("Office of the President — Week ${state.week}") },
        text = {
            Column {
                Text("National Indicators", style = MaterialTheme.typography.titleSmall)
                state.stats.forEach { (k, v) ->
                    Text("${k.replaceFirstChar { it.uppercase() }}: $v / 100")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Cabinet", style = MaterialTheme.typography.titleSmall)
                state.ministers.forEach { m ->
                    Text("${m.portfolio}: ${m.name} (Loyalty ${m.loyalty})")
                }
            }
        }
    )
}
