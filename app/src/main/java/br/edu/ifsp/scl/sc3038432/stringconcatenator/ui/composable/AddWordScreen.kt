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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc3038432.stringconcatenator.ui.theme.StringConcatenatorTheme

@Composable
fun AddWordScreen(
    currentString: String,
    modifier: Modifier,
    onConcatenateClick: (String) -> Unit
) {

    var newWord by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = currentString,
            onValueChange = {},
            readOnly = true,
            label = { Text("String atual") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newWord,
            onValueChange = { newWord = it },
            label = { Text("Nova palavra") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onConcatenateClick(newWord.trim()) },
            enabled = newWord.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Concatenar")
        }
    }
}


@Preview(name = "Light mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AddWordScreenPreview() {
    StringConcatenatorTheme {
        Surface {
            AddWordScreen(
                currentString = "OK Computer",
                modifier = Modifier,
                onConcatenateClick = {}
            )
        }
    }
}