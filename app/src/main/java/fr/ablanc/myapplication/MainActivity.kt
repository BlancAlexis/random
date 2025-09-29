package fr.ablanc.myapplication

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.ablanc.myapplication.ui.theme.MyApplicationTheme
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.FabButtonItem
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.FabButtonMain
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.FabButtonSub
import fr.meteochomeur.kotlinproject.mainScreen.presentation.component.fab_submenu.MultiFloatingActionButton
import kotlinx.coroutines.launch

enum class FABButton(
    val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(), floatingActionButton = {
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
                    },
                    bottomBar = {
                        NavigationBar(

                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.weight(1f, true)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add"
                                )
                            }
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.weight(1f, true)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add"
                                )
                            }
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.weight(1f, true)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add"
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val lazyListState = rememberLazyListState()
    val flightBehavior = rememberSnapFlingBehavior(lazyListState)
    val isHovered by interactionSource.collectIsHoveredAsState()
    val pagerState = rememberPagerState(pageCount = { AutoTabs.entries.size })
    val tabSelectedIndex = remember { derivedStateOf { pagerState.currentPage } }

    Column(
        Modifier.fillMaxSize()
    ) {
        Text(
            text = "Bonjour!"
        )
        LazyRow(
            contentPadding = PaddingValues(start = 32.dp),
            flingBehavior = flightBehavior,
            state = lazyListState
            ) {
            items(
                count = 8,

                ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(if (isHovered) Color.Red else Color.White)
                        .border(2.dp, Color.Black)
                        .hoverable(interactionSource = interactionSource)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Android Logo",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                    )

                    BasicText(text ="Clio Jen-Spaghetti 2004",
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
            selectedTabIndex = tabSelectedIndex.value, modifier = Modifier.fillMaxWidth()
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
            Column {
                Row {
                    Text(text = "Vos prochaines mission", fontWeight = FontWeight.Bold)
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

