package sung.gyun.encryptedsharedpreferencessample.tab_layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import sung.gyun.encryptedsharedpreferencessample.TabRowItem
import sung.gyun.encryptedsharedpreferencessample.ui.theme.EncryptedSharedPreferencesSampleTheme


val tabRowItems = listOf(
    TabRowItem(
        title = "Tab 1",
        screen = { TabScreen(text = "Tab 1", Color.Red) },
        icon = Icons.Rounded.Place,
        background = Color.Red
    ),
    TabRowItem(
        title = "Tab 2",
        screen = { TabScreen(text = "Tab 2", Color.Green) },
        icon = Icons.Rounded.Search,
        background = Color.Green
    ),
    TabRowItem(
        title = "Tab 3",
        screen = { TabScreen(text = "Tab 3", Color.Blue) },
        icon = Icons.Rounded.Star,
        background = Color.Blue
    )
)

@Composable
fun TabScreen(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            text = text,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    EncryptedSharedPreferencesSampleTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()
            TabRow(
                selectedTabIndex = 0,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                        color = MaterialTheme.colors.secondary
                    )
                }
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = null)
                        },
                        text = {
                            Text(
                                text = item.title
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                count = tabRowItems.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
            ) { page ->
                TabScreen(
                    text = tabRowItems[page].title,
                    color = tabRowItems[page].background
                )
            }
        }

    }
}