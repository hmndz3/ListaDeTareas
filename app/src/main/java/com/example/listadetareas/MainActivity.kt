package com.example.listadetareas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listadetareas.ui.theme.ListaDeTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaDeTareasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FondoConLista(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FondoConLista(modifier: Modifier = Modifier) {
    val contexto = LocalContext.current

    var nuevaTarea by remember { mutableStateOf("") }
    val listaTareas = remember { mutableStateListOf<String>() }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondodelaaplicacion),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Título en cuadro blanco, un poco abajo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(Color.White)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.titulo_app),
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto + botón
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = nuevaTarea,
                    onValueChange = { nuevaTarea = it },
                    placeholder = { Text(stringResource(id = R.string.hint_nueva_tarea)) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (nuevaTarea.isBlank()) {
                            // usar getString aquí: NO @Composable
                            Toast.makeText(
                                contexto,
                                contexto.getString(R.string.mensaje_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            listaTareas.add(nuevaTarea)
                            nuevaTarea = ""
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.boton_agregar))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de tareas
            LazyColumn {
                items(listaTareas) { tarea ->
                    Text(
                        text = tarea,
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFondoConLista() {
    ListaDeTareasTheme {
        FondoConLista()
    }
}