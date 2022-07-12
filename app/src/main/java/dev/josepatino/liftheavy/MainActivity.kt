package dev.josepatino.liftheavy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.josepatino.liftheavy.ui.theme.LiftHeavyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiftHeavyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry.value?.destination?.route

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val allScreens = listOf(
        LiftHeavyScreen.Home,
        LiftHeavyScreen.ActiveWorkout,
        LiftHeavyScreen.Exercises
    )

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        topBar = {
            TopAppBar(
                title = { Text("Lift Heavy") },
                navigationIcon = {
                    if (currentScreen == LiftHeavyScreen.Home.route) {
                        IconButton(
                            onClick = {
                                scope.launch { scaffoldState.drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                        }
                    } else {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back Arro Button")
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { /* Add later if needed! */ },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                allScreens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.route) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        content = { innerPadding ->
            Navigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddSetSheetContent(scope: CoroutineScope, scaffoldState: BottomSheetScaffoldState) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Set", fontSize = 22.sp)

        var lbs by rememberSaveable { mutableStateOf("") }
        var reps by rememberSaveable { mutableStateOf("") }
        var difficulty by rememberSaveable { mutableStateOf("") }

        TextField(
            value = lbs,
            onValueChange = { lbs = it },
            label = { Text("Lbs") },
            singleLine = true
        )
        TextField(
            value = reps,
            onValueChange = { reps = it },
            label = { Text("Reps") },
            singleLine = true
        )
        TextField(
            value = difficulty,
            onValueChange = { difficulty = it },
            label = { Text("Difficulty") },
            singleLine = true
        )
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text("Cancel")
            }
            Button(onClick = {
                scope.launch { scaffoldState.bottomSheetState.collapse() }
            }) {
                Text("Add")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun SheetPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    AddSetSheetContent(scope = scope, scaffoldState = scaffoldState)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LiftHeavyTheme {
        App()
    }
}