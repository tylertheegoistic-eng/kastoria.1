package com.statecraft.kastoria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.statecraft.kastoria.model.GameState
import com.statecraft.kastoria.ui.theme.GoldAccent

@Composable
fun DashboardDialog(state: GameState, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        },
        title = {
            Text("Office of the President — Week ${state.week}", style = MaterialTheme.typography.titleMedium)
        },
        text = {
            Column {
                Text("National Indicators", style = MaterialTheme.typography.labelLarge, color = GoldAccent)
                Spacer(modifier = Modifier.height(8.dp))
                state.stats.forEach { (k, v) ->
                    Column(modifier = Modifier.padding(bottom = 8.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = k.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                            Text("$v / 100", style = MaterialTheme.typography.labelLarge)
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        LinearProgressIndicator(
                            progress = { v / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = GoldAccent
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))

                Text("Cabinet", style = MaterialTheme.typography.labelLarge, color = GoldAccent)
                Spacer(modifier = Modifier.height(6.dp))
                state.ministers.forEach { m ->
                    Text(
                        "${m.portfolio}: ${m.name}  (Loyalty ${m.loyalty})",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }
    )
}
