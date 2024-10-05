package com.example.gaiaguard.ui.screen.menu


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
fun MyOptionsList(
    options: List<String>,
    selectedOptions: Set<String>,
    onOptionSelected: (Set<String>) -> Unit
) {
    LazyColumn {
        items(options) { option ->
            var isSelected by remember { mutableStateOf(selectedOptions.contains(option)) }
            TextField(
                value = option,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isSelected = !isSelected
                        onOptionSelected(
                            if (isSelected) selectedOptions + option
                            else selectedOptions - option
                        )
                    }
            )
        }
    }
}

@Composable
fun MyOptionsListApp() {
    val options = listOf("1. Fin de la pobreza", "2. Hambre cero", "3. Salud y bienestar", "4. Educación de calidad",
        "5. Igualdad de género", "6. Agua limpia y saneamiento", "7. Enegía asequible y no contaminante", "8. Trabajo decente y cremiento económico",
        "9. Industria, innovación e infraestructura", "10. Reducción de las desigualdades", "11. Ciudades y comunidades sostenibles", "12. Producción y consumo responsables",
        "13. Agua por el clima", "14. Vida Submarina","15. Vida de ecosistemas","16.Paz, justicia e instituciones solidarias", "17. Alianzas para lograr los objetivos")
    var selectedOptions by remember { mutableStateOf(emptySet<String>()) }

    MyOptionsList(
        options = options,
        selectedOptions = selectedOptions,
        onOptionSelected = { selectedOptions = it }
    )
}