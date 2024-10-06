package com.example.gaiaguard.ui.screen.menu


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gaiaguard.data.model.ObjectiveItem
import com.example.gaiaguard.ui.theme.GaiaGuardTheme


// OptionsScreen (Stateless)
@Composable
fun ObjectivesSelectionScreen(
    options:List<ObjectiveItem>,
    onOptionSelected: (ObjectiveItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(options) { option ->
            ObjectivesItem(option) { onOptionSelected(option) }
        }
    }
}

@Composable
fun ObjectivesItem(
    option: ObjectiveItem,
    onClick: (Int) -> Unit)
{
    Button(
        onClick = { onClick(option.numeroODS) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = option.nombre)}
}

@Preview(showSystemUi = true)
@Composable
fun MenuPreview() {
    GaiaGuardTheme {
        ObjectivesSelectionScreen(options = listOf(
            ObjectiveItem(1, "Fin de la pobreza"),
            ObjectiveItem(2, "Hambre cero"),
            ObjectiveItem(3, "Salud y bienestar"),
            ObjectiveItem(4, "Educación de calidad"),
            ObjectiveItem(5, "Igualdad de género")
        )){

        }
    }
}


