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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listadetareas.ui.theme.ListaDeTareasTheme

data class Task(val text: String, val done: Boolean = false)

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
    val context = LocalContext.current

    var nuevaTarea by remember { mutableStateOf("") }
    val listaTareas = remember { mutableStateListOf<Task>() }

    val completadas = listaTareas.count { it.done }

    Box(modifier = Modifier.fillMaxSize()) {
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
            // Título
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(Color.White)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.titulo_app),
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto + botón Agregar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = nuevaTarea,
                    onValueChange = { nuevaTarea = it },
                    placeholder = { Text(stringResource(R.string.hint_nueva_tarea)) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (nuevaTarea.isBlank()) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.mensaje_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            listaTareas.add(Task(nuevaTarea))
                            nuevaTarea = ""
                        }
                    }
                ) {
                    Text(stringResource(R.string.boton_agregar))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Contador de tareas terminadas
            Text(
                text = stringResource(R.string.tareas_terminadas, completadas),
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de tareas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                itemsIndexed(listaTareas) { index, task ->
                    TaskItem(
                        task = task,
                        onComplete = {
                            // Reemplazar el elemento para que Compose recomponga
                            listaTareas[index] = listaTareas[index].copy(done = true)
                        },
                        onDelete = {
                            listaTareas.removeAt(index)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onComplete: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.text,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None
                ),
                modifier = Modifier.weight(1f)
            )

            // Botón completar (✓)
            IconButton(onClick = onComplete) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.desc_completar),
                    tint = Color(0xFF4CAF50)
                )
            }
            // Botón eliminar (✕)
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(R.string.desc_eliminar),
                    tint = Color(0xFFD32F2F)
                )
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
