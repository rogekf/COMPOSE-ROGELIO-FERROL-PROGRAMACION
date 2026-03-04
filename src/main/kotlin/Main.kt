import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

// Estos delegados son obligatorios para usar 'by'
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

enum class Pantalla { EJ1, EJ2, EJ3, EJ4, EJ5, EJ6 }

@Composable
@Preview
fun App() {
    var pantalla by remember { mutableStateOf<Pantalla?>(null) }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            if (pantalla == null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Menú de Ejercicios", style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(10.dp))

                    Pantalla.values().forEach { ejercicio ->
                        Button(
                            onClick = { pantalla = ejercicio },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(ejercicio.name)
                        }
                    }
                }
            } else {
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(onClick = { pantalla = null }) {
                        Text("<- Volver al menú")
                    }
                    Spacer(Modifier.height(16.dp))

                    when (pantalla) {
                        Pantalla.EJ1 -> Ejercicio1()
                        Pantalla.EJ2 -> Ejercicio2()
                        Pantalla.EJ3 -> Ejercicio3()
                        Pantalla.EJ4 -> Ejercicio4()
                        Pantalla.EJ5 -> Ejercicio5()
                        Pantalla.EJ6 -> Ejercicio6()
                        null -> {}
                    }
                }
            }
        }
    }
}










// --- IMPLEMENTACIÓN REAL DE LOS EJERCICIOS ---

@Composable
fun Ejercicio1() {
    var texto by remember { mutableStateOf("Haz clic aquí") }
    Button(onClick = { texto = "¡Has pulsado el botón!" }) {
        Text(texto)
    }
}

@Composable
fun Ejercicio2() {
    var visible by remember { mutableStateOf(false) }
        Text("Mostrar texto")

        Button(onClick = { visible = !visible }) {
            Text(if (visible) "Ocultar" else "Mostrar")
        }
    Button(onClick = { visible =! visible }) {
        Text(if (visible) "Mostrar" else "Ocultar")
    }
        if (visible) {
            Text("¡Hola! Soy un texto dinámico.", modifier = Modifier.padding(top = 8.dp))
        }
    }


@Composable
fun Ejercicio3() {
    var seleccion by remember { mutableStateOf("Ninguna") }
    Column {
        Text("Submenú:")
        Row {
            Button(onClick = { seleccion = "Opción A" }, modifier = Modifier.padding(4.dp)) { Text("A") }
            Button(onClick = { seleccion = "Opción B" }, modifier = Modifier.padding(4.dp)) { Text("B") }
        }
        Text("Seleccionaste: $seleccion")
    }
}

@Composable
fun Ejercicio4() {
    var contador by remember { mutableStateOf(0) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = { contador-- }) { Text("-") }
        Text(" $contador ", style = MaterialTheme.typography.h4, modifier = Modifier.padding(horizontal = 16.dp))
        Button(onClick = { contador++ }) { Text("+") }
    }
}

@Composable
fun Ejercicio5() {
    var input by remember { mutableStateOf("") }
    Column {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Escribe algo...") }
        )
        Text("Resultado: $input")
    }
}

@Composable
fun Ejercicio6() {
    // Estado del tablero: una lista de 9 booleanos
    val celdas = remember { mutableStateListOf(*Array(9) { false }) }

    Column {
        Text("Tablero 3x3")
        Spacer(Modifier.height(8.dp))
        for (fila in 0..2) {
            Row {
                for (col in 0..2) {
                    val index = fila * 3 + col
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .border(1.dp, Color.Gray)
                            .clickable { celdas[index] = true },
                        contentAlignment = Alignment.Center
                    ) {
                        if (celdas[index]) Text("\u2714", style = MaterialTheme.typography.h5)
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Ejercicios Compose Desktop") {
        App()
    }
}