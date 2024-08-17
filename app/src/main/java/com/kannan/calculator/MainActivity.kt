package com.kannan.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.kannan.calculator.presentation.CalculatorScreen
import com.kannan.calculator.presentation.CalculatorViewModel
import com.kannan.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val viewModel: CalculatorViewModel by viewModels()
                val uiState by viewModel.uiState.collectAsState()
                CalculatorScreen(
                    uiState = uiState,
                    uiAction = viewModel::onUiAction
                )
            }
        }
    }
}