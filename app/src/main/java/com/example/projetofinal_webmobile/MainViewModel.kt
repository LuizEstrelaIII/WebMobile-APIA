package com.example.projetofinal_webmobile

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _fact = MutableStateFlow("Carregando curiosidade...")
    val fact: StateFlow<String> = _fact

    private val _backgroundColor = MutableStateFlow(Color.Black)
    val backgroundColor: StateFlow<Color> = _backgroundColor

    private val colors = listOf(Color.Red, Color.Yellow, Color.Blue, Color.Cyan, Color.Green)

    init {
        fetchAndDisplayFact()
    }

    fun fetchAndDisplayFact() {
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "Fetching fact from API")
                val response = withContext(Dispatchers.IO) {
                    URL("http://numbersapi.com/random/trivia").readText()
                }
                Log.d("MainViewModel", "API response: $response")
                val randomColor = colors[Random.nextInt(colors.size)]
                _fact.update { response }
                _backgroundColor.update { randomColor }
                Log.d("MainViewModel", "Updated fact and background color")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching fact", e)
                _fact.update { "Ops! Não foi possível obter uma curiosidade." }
            }
        }
    }
}
