package com.statecraft.kastoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.statecraft.kastoria.ui.BudgetScreen
import com.statecraft.kastoria.ui.CabinetScreen
import com.statecraft.kastoria.ui.DashboardDialog
import com.statecraft.kastoria.ui.EndingScreen
import com.statecraft.kastoria.ui.EventScreen
import com.statecraft.kastoria.ui.theme.CardBackground
import com.statecraft.kastoria.ui.theme.GoldAccent
import com.statecraft.kastoria.ui.theme.KastoriaTheme
import com.statecraft.kastoria.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KastoriaTheme {
                GameApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameApp(viewModel: GameViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    var showDashboard by remember { mutableStateOf(false) }
    val node = viewModel.currentNode()
    val choices = viewModel.availableChoices()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "KASTORIA · Week ${state.week}",
                        style = MaterialTheme.typography.titleLarge,
                        color = GoldAccent
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CardBackground),
                actions = {
                    IconButton(onClick = { showDashboard = true }) {
                        Icon(Icons.Filled.Info, contentDescription = "Office Dashboard", tint = GoldAccent)
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                node == null -> Text("No content loaded.", modifier = Modifier.padding(20.dp))
                node.type == "budget" -> BudgetScreen(
                    node = node,
                    budget = state.budget,
                    choices = choices,
                    onBudgetChange = { viewModel.updateBudget(it) },
                    onConfirm = { viewModel.confirmBudget(it) }
                )
                node.type == "cabinet" -> CabinetScreen(
                    node = node,
                    ministers = state.ministers,
                    choices = choices,
                    onChoose = { viewModel.choose(it) }
                )
                node.type == "ending" -> EndingScreen(
                    node = node,
                    state = state,
                    onRestart = { viewModel.restart() }
                )
                else -> EventScreen(
                    node = node,
                    choices = choices,
                    onChoose = { viewModel.choose(it) }
                )
            }
        }
        if (showDashboard) {
            DashboardDialog(state = state, onDismiss = { showDashboard = false })
        }
    }
}
