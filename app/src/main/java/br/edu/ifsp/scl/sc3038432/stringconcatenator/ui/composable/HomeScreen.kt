package br.edu.ifsp.scl.sc3038432.stringconcatenator.ui.composable

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc3038432.stringconcatenator.ui.theme.StringConcatenatorTheme

@Composable
fun HomeScreen(
    returnedWord: String?,
    modifier: Modifier,
    onWordConsumed: () -> Unit,
    onAddWordClick: (String) -> Unit
) {
    var currentString by rememberSaveable { mutableStateOf("") }

    // Compose recria a tela a cada recomposição
    // LaunchedEffect(LE) cria um escopo de corrotina qnd o componente entra na tela
    // Se o componente sair da tela (destruído), o LE cancela a tarefa rodando dentro dele
    LaunchedEffect( key1 = returnedWord ) { // aceita mútiplas keys
        // se o valor da chave mudar cancela a task antiga e começa uma nova
        if ( returnedWord != null ) {
            currentString = if ( currentString.isEmpty() ) returnedWord else "$currentString $returnedWord"
            onWordConsumed()
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = currentString,
            onValueChange = {},
            readOnly = true,
            label = { Text("String atual") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onAddWordClick(currentString) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar palavra")
        }

        Button(
            onClick = { currentString = "" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar")
        }
    }
}


@Preview(name = "Light mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    StringConcatenatorTheme {
        Surface {
            HomeScreen(
                returnedWord = null,
                modifier = Modifier,
                onWordConsumed = {},
                onAddWordClick = {}
            )
        }
    }
}