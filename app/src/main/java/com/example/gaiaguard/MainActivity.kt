package com.example.gaiaguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gaiaguard.ui.screen.menu.MyOptionsList
import com.example.gaiaguard.ui.theme.GaiaGuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyOptionsListApp()
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

