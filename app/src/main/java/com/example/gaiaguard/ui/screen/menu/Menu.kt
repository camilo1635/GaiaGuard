package com.example.gaiaguard.ui.screen.menu


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gaiaguard.R
import com.example.gaiaguard.data.model.ObjetiveItem
import com.example.gaiaguard.ui.theme.GaiaGuardTheme


// OptionsScreen (Stateless)
@Composable
fun ObjectivesSelectionScreen(options:List<ObjetiveItem>, onOptionSelected: (ObjetiveItem) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(options) { option ->
            ObjectivesItem(option) { onOptionSelected(option) }
        }
    }
}

@Composable
fun ObjectivesItem(
    option: ObjetiveItem,
    onClick: (Int) -> Unit)
{
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF167D78)),
        onClick = { onClick(option.id) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = option.label)}
}

@Preview(showSystemUi = true)
@Composable
fun MenuPreview() {
    GaiaGuardTheme {
        ObjectivesSelectionScreen(options = listOf(
            ObjetiveItem(1, "Fin de la pobreza"),
            ObjetiveItem(2, "Hambre cero"),
            ObjetiveItem(3, "Salud y bienestar"),
            ObjetiveItem(4, "Educación de calidad"),
            ObjetiveItem(5, "Igualdad de género")
        )){

        }
    }
}


