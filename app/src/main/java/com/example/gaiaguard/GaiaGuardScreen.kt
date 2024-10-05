package com.example.gaiaguard

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gaiaguard.ui.screen.welcome.WelcomeScreen
import javax.sql.DataSource


enum class GaiaGuardScreen(@StringRes val title: Int) {
    Welcome(title = R.string.app_name)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaiaGuardAppBar(
    currentScreen: GaiaGuardScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun GaiaGuardApp(
    gaiaGuardViewModel: GaiaGuardViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val welcomeUiState by gaiaGuardViewModel.welcomeUiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GaiaGuardScreen.valueOf(
        backStackEntry?.destination?.route ?: GaiaGuardScreen.Welcome.name
    )
    Scaffold(
        topBar = {
            GaiaGuardAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }

    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = GaiaGuardScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            
            composable(route = GaiaGuardScreen.Welcome.name) {
                WelcomeScreen(
                    onNameEntered = { gaiaGuardViewModel.updateParticipantName(it) },
                    onStartGame = {  })
            }

            /*
                        composable(route = CupcakeScreen.Flavor.name) {
                            val context = LocalContext.current
                            SelectOptionScreen(
                                subtotal = uiState.price,
                                onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
                                onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                                options = DataSource.flavors.map { id -> context.resources.getString(id) },
                                onSelectionChanged = { viewModel.setFlavor(it) },
                                modifier = Modifier.fillMaxHeight()
                            )
                        }
            
                         */
        }
    }
}