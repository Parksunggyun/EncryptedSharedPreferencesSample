package sung.gyun.encryptedsharedpreferencessample.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import sung.gyun.encryptedsharedpreferencessample.ui.theme.EncryptedSharedPreferencesSampleTheme

@Composable
fun Buttons() {

    LazyColumn {
        items(1) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Text("Button")
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            ) {
                Text("Outlined Button")
            }
            TextButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {
                Text("Text Button")
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),
            ) {
                Icon(Icons.Filled.Settings, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    EncryptedSharedPreferencesSampleTheme {
        Column {
            Buttons()
        }
    }
}