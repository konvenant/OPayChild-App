package com.example.opaychild.presentations.ui.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.data.dataSource.UserPreferences
import com.example.opaychild.data.repository.UserRepositoryImpl
import com.example.opaychild.domain.GetUserUseCase
import com.example.opaychild.presentations.ui.screens.home.bottomNavigation.DashboardScreen
import com.example.opaychild.presentations.ui.screens.home.bottomNavigation.FriendsScreen
import com.example.opaychild.presentations.ui.screens.home.bottomNavigation.RewardScreen
import com.example.opaychild.presentations.ui.screens.onboarding.welcome.SplashScreen
import com.example.opaychild.presentations.viewmodels.UserViewModel

@Composable
fun BottomNavGraph(
    userViewModel: UserViewModel,
    mainNavController: NavController,
    navController: NavHostController
){


    NavHost(navController, startDestination = NavHelper.DashboardScreen.route) {
        composable(NavHelper.DashboardScreen.route) {
            DashboardScreen(navController,mainNavController)
        }
        composable(NavHelper.RewardScreen.route) {
            RewardScreen()
        }
        composable(NavHelper.FriendsScreen.route) {
            FriendsScreen(navController)
        }
    }
}

@Preview
@Composable
fun BtmNavPrev(){
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val userRepository  = UserRepositoryImpl(userPreferences)
    val getUserUseCase = GetUserUseCase(userRepository)
    val userViewModel = UserViewModel(getUserUseCase)

    BottomNavGraph(userViewModel,rememberNavController(), rememberNavController())
}