package com.example.holatoast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.holatoast.ui.theme.HolatoastTheme
import androidx.compose.runtime.saveable.rememberSaveable  // ← Importación corregida

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HolatoastTheme {
                UIPrincipal()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Agrega esto si hay advertencias con Material3
@Composable
fun UIPrincipal() {
    val contexto = LocalContext.current
    var nombre by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            SmallTopAppBar( // Usa SmallTopAppBar en lugar de TopAppBar si hay problemas
                title = { Text("Hola Toast App") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Nombre:")
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },  // ← Asegurarse de que "it" esté correctamente referenciado
                label = { Text("Introduce tu nombre") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Toast.makeText(contexto, "Hola $nombre!!", Toast.LENGTH_LONG).show()
                }
            ) {
                Text("Saludar!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HolatoastTheme {
        UIPrincipal()
    }
}
