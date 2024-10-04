package com.github.juanncode.challengeapuestatotal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.juanncode.challengeapuestatotal.screens.home.HomeScreen
import com.github.juanncode.challengeapuestatotal.screens.home.HomeViewModel
import com.github.juanncode.challengeapuestatotal.screens.login.LoginScreen
import com.github.juanncode.challengeapuestatotal.screens.login.LoginViewModel
import com.github.juanncode.challengeapuestatotal.ui.theme.ChallengeApuestaTotalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isLoading
            }
        }
        enableEdgeToEdge()
        setContent {
            ChallengeApuestaTotalTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = AppRouter.LoginRoute,

                        ) {
                        composable<AppRouter.HomeRoute> {
                            val viewModel = hiltViewModel<HomeViewModel>()
                            HomeScreen(
                                state = viewModel.state,
                                onEvent = {
                                    viewModel.onEvent(it)
                                },
                                navController = navController
                            )
                        }
                        composable<AppRouter.LoginRoute> {
                            val viewModel = hiltViewModel<LoginViewModel>()
                            LoginScreen(
                                state = viewModel.state,
                                onEvent = {
                                    viewModel.onEvent(it)
                                },
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }
}

