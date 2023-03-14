package sung.gyun.encryptedsharedpreferencessample.top_app_bar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sung.gyun.encryptedsharedpreferencessample.ui.theme.EncryptedSharedPreferencesSampleTheme


@Composable
fun TopAppBarSample() {
    TopAppBar(
        elevation = 4.dp,
        title = { Text("Top App Bar", textAlign = TextAlign.Center) },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Share, null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Settings, null)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarSamplePreview() {
    EncryptedSharedPreferencesSampleTheme {
        TopAppBarSample()
    }
}