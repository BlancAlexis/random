package fr.ablanc.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import fr.ablanc.myapplication.ui.theme.MyApplicationTheme
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.FabButtonItem
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.FabButtonMain
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.FabButtonSub
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.MultiFloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

enum class FABButton(
    val label: String, val icon: ImageVector
) {
    ADD("Add", Icons.Filled.Add), STAR("Star", Icons.Filled.Star), BUILD(
        "Build", Icons.Filled.Build
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
                    MultiFloatingActionButton(
                        items = FABButton.entries.map {
                            FabButtonItem(
                                label = it.label, iconRes = it.icon
                            )
                        }, onFabItemClicked = { item ->
                            when (item.label) {
                                FABButton.STAR.label -> {
                                }

                                FABButton.ADD.label -> {
                                }

                                FABButton.BUILD.label -> {

                                }

                            }
                        }, fabIcon = FabButtonMain(), fabOption = FabButtonSub()
                    )
                }, bottomBar = {
                    NavigationBar(

                    ) {
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier.weight(1f, true)

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add, contentDescription = "Add"
                            )
                        }
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier.weight(1f, true)

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add, contentDescription = "Add"
                            )
                        }
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier.weight(1f, true)

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add, contentDescription = "Add"
                            )
                        }
                    }
                }) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class VehiculeHeader(
    val name: String,
    val image: Int = R.drawable.ic_launcher_background,
    val uiStyle: ImageDecor = ImageDecor.Normal
)

sealed class ImageDecor(
    val size: Dp, val border: Dp, val color: Color
) {
    object Selected : ImageDecor(120.dp, 3.dp, Color.Red)
    object Normal : ImageDecor(100.dp, 1.dp, Color.Gray)
}

@Composable
fun ListItemView(modifier: Modifier = Modifier) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Build, contentDescription = ""
            )
        },
        headlineContent = { Text(text = "Headline") },
        supportingContent = {
            LinearProgressIndicator(
                progress = {
                    0.6f
                },
                color = Color.Red,
                trackColor = Color.Green,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        },
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState)
    val isHovered by interactionSource.collectIsHoveredAsState()
    val pagerState = rememberPagerState(pageCount = { AutoTabs.entries.size })
    val tabSelectedIndex = remember { derivedStateOf { pagerState.currentPage } }
    val list = remember {
        List(20) { index -> VehiculeHeader("Clio $index", R.drawable.ic_launcher_background) }
    }
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableIntStateOf(0) }

    // debug dans Logcat — attention : utiliser Logcat pour voir ces messages
    LaunchedEffect(lazyListState) {
        Log.d("GreetingFixed", "LaunchedEffect started")
        snapshotFlow { lazyListState.firstVisibleItemIndex }.distinctUntilChanged()
            .collectLatest { idx ->
                Log.d("GreetingFixed", "firstVisibleItem = $idx")
                selectedIndex = idx
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "SelectedIndex = $selectedIndex", modifier = Modifier.padding(8.dp))
        LazyRow(
            contentPadding = PaddingValues(start = LocalConfiguration.current.screenWidthDp.dp / 3),
            flingBehavior = flingBehavior,
            state = lazyListState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(bottom = 32.dp)
        ) {
            itemsIndexed(list) { index, item ->
                val scale by animateFloatAsState(
                    targetValue = if (index == selectedIndex) 1.4f else 1f,
                    animationSpec = spring(dampingRatio = 0.6f)
                )

                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(140.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (index == selectedIndex) Color(0xFFEA4335) else Color.LightGray)
                        .hoverable(interactionSource = interactionSource)
                        .clickable {
                            scope.launch {
                                lazyListState.animateScrollToItem(index)
                            }
                        }
                        .zIndex(if (index == selectedIndex) 1f else -1f),
                    contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )

                    BasicText(
                        text = item.name,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
        TabRow(
            selectedTabIndex = tabSelectedIndex.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        ) {
            AutoTabs.entries.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier,
                    icon = {
                        Image(
                            imageVector = if (tabSelectedIndex.value == index) {
                                tab.selectedIcon
                            } else {
                                tab.unselectedIcon
                            }, contentDescription = tab.text
                        )
                    },
                    selected = tabSelectedIndex.value == index,
                    text = { Text(text = tab.text) },
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },

                    )
            }
        }
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row {
                    Text(text = "Vos prochaines mission", fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Yellow),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Critisité", fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    LazyColumn(
                        modifier = Modifier.indicatorLine(
                            enabled = true,
                            isError = false,
                            colors = TODO(),
                            focusedIndicatorLineThickness = TODO(),
                            unfocusedIndicatorLineThickness = TODO()
                        )
                    ) {
                        items(10) {
                            ListItemView()
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxSize()
                        .background(Color.Yellow)
                ) {

                }


            }
        }
        MapRow()
    }
}

@Composable
fun MapRow(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red)
    ) {

    }
}

enum class AutoTabs(
    val selectedIcon: ImageVector, val unselectedIcon: ImageVector, val text: String
) {
    FUTURE(
        selectedIcon = Icons.Outlined.AccountBox,
        unselectedIcon = Icons.Filled.AccountBox,
        text = "Prochaine date"

    ),
    PAST(
        selectedIcon = Icons.Outlined.AccountBox,
        unselectedIcon = Icons.Filled.AccountBox,
        text = "Date passée"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

