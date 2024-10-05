package com.example.gaiaguard.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gaiaguard.R

@Composable
fun WelcomeScreen(
    onNameEntered: (String) -> Unit,
    onStartGame: () -> Unit
) {
    var participantName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título del juego
        Text(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Imagen del juego (reemplázala con tu imagen)
        Image(
            painter = painterResource(id = R.drawable.icono), // Reemplaza con tuimagen
            contentDescription = "Descripción del juego",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Descripción del juego
        Text(
            text = stringResource(id = R.string.app_description),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Campo de entrada para el nombre
        OutlinedTextField(
            value = participantName,
            onValueChange = {  },
            label = { Text("Ingresa tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para comenzar el juego
        Button(
            onClick = {
                onNameEntered(participantName)
                onStartGame()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¡Comenzar juego!")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onNameEntered = {},
        onStartGame = {}
    )
}