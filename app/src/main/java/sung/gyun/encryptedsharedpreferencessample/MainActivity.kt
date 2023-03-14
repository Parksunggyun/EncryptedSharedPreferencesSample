package sung.gyun.encryptedsharedpreferencessample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sung.gyun.encryptedsharedpreferencessample.ui.theme.EncryptedSharedPreferencesSampleTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncryptedSharedPreferencesSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SaveAndLoadData()
                }
            }
        }
    }
}

@Composable
fun SaveAndLoadData() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        var name by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var nickname by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var birth by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(width = 2.dp, color = Color.Black, shape = RectangleShape),
            text = "데이터 저장 및 불러오기",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            placeholder = {
                Text("이름을 입력하세요.")
            },
            value = name, onValueChange = {
                name = it
            })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            label = {
                Text("별명")
            },
            placeholder = {
                Text("별명을 입력하세요.")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Warning, contentDescription = "Birth")
            },
            value = nickname, onValueChange = {
                nickname = it
            })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Birth")
            },
            value = birth,
            label = {
                Text("생년월일")
            },
            placeholder = {
                Text("19920822")
            },
            onValueChange = {
                birth = it
            },
        )
        var savedName by remember {
            mutableStateOf("")
        }
        var savedNickname by remember {
            mutableStateOf("")
        }
        var savedBirth by remember {
            mutableStateOf("")
        }

        SharedPreferenceManager.getBasicSharedPref(context).run {
            savedName = getString("name", "Unknown").toString()
            savedNickname = getString("nickName", "Unknown").toString()
            savedBirth = getString("birth", "Unknown").toString()
        }

        var encName: String
        var encNickname: String
        var encBirth: String
        SharedPreferenceManager.getEncryptedSharedPref(context).run {
            encName = getString("name", "Unknown").toString()
            encNickname = getString("nickName", "Unknown").toString()
            encBirth = getString("birth", "Unknown").toString()
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .fillMaxHeight()) {

            Text(text = savedName)
            Text(text = savedNickname)
            Text(text = savedBirth)

            Text(text = encName)
            Text(text = encNickname)
            Text(text = encBirth)
        }
        Row(modifier = Modifier.fillMaxWidth().height(64.dp)) {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {
                    val edit = SharedPreferenceManager.getBasicSharedPref(context).edit()
                    edit.run {
                        if(name.text.isNotEmpty()) {
                            putString("name", name.text)
                        }
                        if(nickname.text.isNotEmpty()) {
                            putString("nickName", nickname.text)
                        }
                        if(birth.text.isNotEmpty()) {
                            putString("birth", birth.text)
                        }
                        apply()
                    }

                    SharedPreferenceManager.getBasicSharedPref(context).run {
                        savedName = getString("name", "Unknown").toString()
                        savedNickname = getString("nickName", "Unknown").toString()
                        savedBirth = getString("birth", "Unknown").toString()
                    }
                }) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
                    text ="Save Data"
                )
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {
                    SharedPreferenceManager.migrate(context)
                }) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
                    text ="Migration"
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SaveAndDataPreview() {
    EncryptedSharedPreferencesSampleTheme {
        SaveAndLoadData()
    }
}