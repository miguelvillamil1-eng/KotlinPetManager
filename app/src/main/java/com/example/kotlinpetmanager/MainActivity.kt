package com.example.kotlinpetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.kotlinpetmanager.ui.PacienteScreen
import com.example.kotlinpetmanager.viewmodel.PacienteViewModel

class MainActivity : ComponentActivity() {

    private val pacienteViewModel: PacienteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    PacienteScreen(viewModel = pacienteViewModel)
                }
            }
        }
    }
}