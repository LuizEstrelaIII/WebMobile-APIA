package com.example.webmobile_api

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import com.example.webmobile_api.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, userName: String?) {
    val viewModel: MainViewModel = viewModel()
    val fact by viewModel.fact.collectAsState()
    val backgroundColor by viewModel.backgroundColor.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Curiosidades Aleatórias de $userName") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = fact,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.fetchAndDisplayFact()
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Nova Curiosidade")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Voltar")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.imagem_projeto_wm),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}
