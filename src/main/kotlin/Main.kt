import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.SpanStyle

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
    Column(modifier = Modifier.padding(16.dp)){ //Modifica el padding
        Text("Podemos visualizar o no un texto")

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
            Button(onClick = { visible = true }) {
                Text("Mostrar")
            }
            Button(onClick = { visible = false }) {
                Text("Ocultar")
            }
        }
        if (visible) {
            Text("¡Hola! Soy un texto dinámico.", modifier = Modifier.padding(top = 8.dp))
        }
    }
    }


@Composable
fun Ejercicio3() {
    var subPantalla by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Experimentamos creando una especie de submenu basado en botones")
        Spacer(modifier = Modifier.height(10.dp))
        when(subPantalla){
            0-> {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
                    Button(onClick = {subPantalla=1}){
                        Text("Ir a Opción 1")
                    }
                    Button(onClick = {subPantalla=2}){
                        Text("Ir a Opción 2")
                    }
                }
            }
            1-> {
                Text("Pantalla Opción 1")
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {subPantalla=0}){
                    Text("Volver")
                }
            }
            2->
            {Text("Pantalla Opción 2")
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {subPantalla=0}){
                    Text("Volver")
                }
            }
        }

    }
}

@Composable
fun Ejercicio4() {
    var contador by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)){
        Text("Tipico ejemplo de incremento de contador")
        Text("Contador: $contador")
        Spacer(modifier = Modifier.height(10.dp) )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
            Button(onClick = {contador++}){
                Text("Incrementar")
            }
            Button(onClick = {contador=0}){
                Text("Reiniciar")
            }
        }
    }
}

@Composable
fun Ejercicio5() {
    var nombre by remember { mutableStateOf("") }

    var mensajeConfirmado by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)){
        Text("Practicamos con el TextField")
        Text("Introduce tu nombre:")
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.isNotBlank()){
                mensajeConfirmado="Hola, $nombre"
            }
        }){
            Text("Saludar")
        }
        if (mensajeConfirmado.isNotEmpty()){
            Spacer(modifier = Modifier.height(16.dp))
            Text(mensajeConfirmado, style = MaterialTheme.typography.h6)
        }
    }
    }


@Composable
fun Ejercicio6() {
    // Estado del tablero: una lista de 9 booleanos
    val celdas = remember { List(3){ MutableList(3){ mutableStateOf(' ') } } }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Dibujamos un tablero de 3x3. Cada elemento del tablero es un box y es clickeable. Al hacer clic en cada celda colocamos una marca '\\\\u2714'")
        Spacer(Modifier.height(8.dp))
        Spacer(Modifier.height(16.dp))

        Column(modifier = Modifier.border(1.dp,Color.Black)){
            for (fila in 0..2) {
                Row {
                    for (col in 0..2) {
                        val index = fila * 3 + col
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .border(0.5.dp, Color.Black)
                                //.background(if (celdas[fila][col]) Color(0xFFE0E0E0) else Color.White)
                                .clickable { celdas[fila][col].value = '✔' },

                            contentAlignment = Alignment.Center

                        ) {
                            Text("${celdas[fila][col].value}")
                        }
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