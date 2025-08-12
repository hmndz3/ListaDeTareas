package com.example.listadetareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.listadetareas.ui.theme.ListaDeTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaDeTareasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FondoConTitulo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FondoConTitulo(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondodelaaplicacion),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Título en la parte superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = "A por Ellas",
                color = Color.Black,
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif, // Usamos Serif como sustituto de Times New Roman
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFondoConTitulo() {
    ListaDeTareasTheme {
        FondoConTitulo()
    }
}
