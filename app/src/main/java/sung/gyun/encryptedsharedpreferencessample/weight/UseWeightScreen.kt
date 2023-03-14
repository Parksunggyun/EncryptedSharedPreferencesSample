package sung.gyun.encryptedsharedpreferencessample.weight

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import sung.gyun.encryptedsharedpreferencessample.ui.theme.EncryptedSharedPreferencesSampleTheme
import sung.gyun.encryptedsharedpreferencessample.R


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UseWeightScreen(@DrawableRes imageId: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "This is title.",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colors.onSurface)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight()
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn {
                items(30) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.White),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.background(Color.Yellow),
                            painter = painterResource(id = imageId),
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            text = "${index}번쨰 아이템",
                            fontSize = 30.sp,
                            style = TextStyle(
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
        Box(contentAlignment = Alignment.BottomCenter) {
            Text(
                text = "This is bottom.",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(MaterialTheme.colors.onSurface)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UseWeightScreenPreview() {
    EncryptedSharedPreferencesSampleTheme {
        UseWeightScreen(R.drawable.ic_android_black_24dp)
    }
}