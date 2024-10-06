package com.example.gaiaguard.ui.screen.levels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gaiaguard.R

@Composable
fun LevelSelectionScreen(
    onLevelSelected: (Int) -> Unit,
    name: String,
    objective: String,
    modifier: Modifier = Modifier) {

    var selectedLevel by remember { mutableStateOf(1) } // Nivel seleccionado por defecto

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$name, tu objetivo es:",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = objective,
            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(128.dp))

        Text(
            text = stringResource(id = R.string.level_description),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(128.dp))

        // Botones de selección de nivel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            LevelButton(
                level = 1,
                isSelected = selectedLevel == 1,
                onClick = { selectedLevel = 1 }
            )
            LevelButton(
                level = 2,
                isSelected = selectedLevel == 2,
                onClick = { selectedLevel = 2 }
            )
            LevelButton(
                level = 3,
                isSelected = selectedLevel == 3,
                onClick = { selectedLevel = 3 }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para continuar
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF167D78)),
            onClick = { onLevelSelected(selectedLevel) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.continue_button))
        }
    }
}

@Composable
fun LevelButton(level: Int, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF167D78) else Color.Gray, // Cambia el color del contenedor si está seleccionado
            contentColor = Color.White // Cambia el color del texto
        )
    ) {
        Text("Nivel $level")
    }
}

@Composable
@Preview(showSystemUi = true)
fun LevelSelectionScreenPreview() {
    LevelSelectionScreen(
        onLevelSelected = {},
        name = "Camilo",
        objective = "Acabar el hambre")
}
