package com.example.gaiaguard.ui.screen.item

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gaiaguard.data.model.Item
import com.example.gaiaguard.ui.theme.GaiaGuardTheme

@Composable
fun ItemDetailsScreen(
    item: Item,
    context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen
        AsyncImage(
            model = item.linkimagen,
            contentDescription = item.descripcion,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = item.palabra ?: "Título no disponible",
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Descripción
        Text(
            text = item.descripcion ?: "Descripción no disponible",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily)
            )

        Spacer(modifier = Modifier.height(16.dp))

        ClickableText(
            text = AnnotatedString(item.linkBio ?: "No hay referencias disponibles"),
            onClick = {
                item.linkBio?.let { link ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    context.startActivity(intent)
                }
            },
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            )
        )
    }

}

@Composable
@Preview(showSystemUi = true)
private fun ItemScreenPreview() {
    GaiaGuardTheme {
        ItemDetailsScreen(
            item = Item(
                descripcion = "Descripción del artículo",
                linkimagen = "https://firebasestorage.googleapis.com/v0/b/nasa-space-challenge-a1c0e.appspot.com/o/ODS%2F15%2Fitems%2F1728190966281_depositphotos_13469325-stock-photo-sun-in-space.jpg?alt=media&token=2fcef651-1967-4a24-9e6e-d992f23c0f1a",
                palabra = "Título del artículo",
                referencia = "https://ejemplo.com/referencia"),
            context = LocalContext.current)
    }
}
