package com.example.opaychild.presentations.ui.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.data.dataSource.UserPreferences
import com.example.opaychild.data.repository.UserRepository
import com.example.opaychild.data.repository.UserRepositoryImpl
import com.example.opaychild.domain.GetUserUseCase
import com.example.opaychild.presentations.ui.screens.actions.extra.StoryScreen
import com.example.opaychild.presentations.ui.screens.actions.extra.SupportScreen
import com.example.opaychild.presentations.ui.screens.actions.mobile.AirtimeScreen
import com.example.opaychild.presentations.ui.screens.actions.mobile.DataScreen
import com.example.opaychild.presentations.ui.screens.actions.savings.GoalsScreen
import com.example.opaychild.presentations.ui.screens.actions.savings.SavingsScreen
import com.example.opaychild.presentations.ui.screens.actions.transaction.DepositScreen
import com.example.opaychild.presentations.ui.screens.actions.transaction.SubscriptionScreen
import com.example.opaychild.presentations.ui.screens.actions.transaction.WithdrawScreen
import com.example.opaychild.presentations.ui.screens.actions.transfer.ConfirmTransferScreen
import com.example.opaychild.presentations.ui.screens.actions.transfer.TransferToBankAccountScreen
import com.example.opaychild.presentations.ui.screens.actions.transfer.TransferToBankScreen
import com.example.opaychild.presentations.ui.screens.actions.transfer.TransferToFriendScreen
import com.example.opaychild.presentations.ui.screens.actions.transfer.TransferToOPayAccountScreen
import com.example.opaychild.presentations.ui.screens.actions.transfer.TransferToOPayScreen
import com.example.opaychild.presentations.ui.screens.home.CardScreen
import com.example.opaychild.presentations.ui.screens.home.HomeScreen
import com.example.opaychild.presentations.ui.screens.home.actions.HistoryScreen
import com.example.opaychild.presentations.ui.screens.home.actions.TransactionHistoryScreen
import com.example.opaychild.presentations.ui.screens.home.actions.TransactionReceiptScreen
import com.example.opaychild.presentations.ui.screens.home.user.MessageScreen
import com.example.opaychild.presentations.ui.screens.home.user.Profile
import com.example.opaychild.presentations.ui.screens.home.user.ProfileScreen
import com.example.opaychild.presentations.ui.screens.home.user.SettingsScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.ConnectToParentScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.LoginScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.RegisterChoiceScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.RegisterScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.ResetPasswordScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.VerifyScreen
import com.example.opaychild.presentations.ui.screens.onboarding.setup.RegisterUserDetailsScreen
import com.example.opaychild.presentations.ui.screens.onboarding.welcome.SplashScreen
import com.example.opaychild.presentations.ui.screens.onboarding.welcome.WelcomeScreen
import com.example.opaychild.presentations.viewmodels.UserViewModel

@Composable
fun MainGraph(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val userRepository  = UserRepositoryImpl(userPreferences)
    val getUserUseCase = GetUserUseCase(userRepository)
    val userViewModel = UserViewModel(getUserUseCase)
    NavHost(navController, startDestination = NavHelper.SplashScreen.route){
     composable(NavHelper.SplashScreen.route){
           SplashScreen( navController, userViewModel)
     }

        composable(NavHelper.WelcomeScreen.route){
          WelcomeScreen(navController)
        }

        composable(NavHelper.LoginScreen.route){
         LoginScreen(navController)
        }

        composable(NavHelper.RegisterScreen.route){
          RegisterScreen(navController, userViewModel)
        }

        composable(NavHelper.RegisterUserDetailsScreen.route){
           RegisterUserDetailsScreen(navController, userViewModel)
        }

        composable(NavHelper.RegisterChoiceScreen.route){
          RegisterChoiceScreen(navController, userViewModel)
        }

        composable(NavHelper.ConnectToParentScreen.route){
          ConnectToParentScreen(navController, userViewModel)
        }

        composable(NavHelper.VerifyScreen.route){
           VerifyScreen(navController, userViewModel)
        }

        composable(NavHelper.ResetPasswordScreen.route){
          ResetPasswordScreen(navController, userViewModel)
        }

        composable(NavHelper.HomeScreen.route){
            HomeScreen(
                navController, userViewModel
            )
        }

        composable(NavHelper.TransactionsScreen.route){
            TransactionHistoryScreen(navController)
        }

        composable(NavHelper.TransactionReceiptScreen.route){
            TransactionReceiptScreen(navController)
        }

        composable(NavHelper.TransferToFriendScreen.route){
            TransferToFriendScreen(navController)
        }

        composable(NavHelper.TransferToBankScreen.route){
            TransferToBankScreen(navController)
        }
        composable(NavHelper.TransferToOPayScreen.route){
            TransferToOPayScreen(navController)
        }

        composable(NavHelper.MakePaymentScreen.route){
            ConfirmTransferScreen(navController)
        }

        composable(NavHelper.WithdrawScreen.route){
            WithdrawScreen(navController)
        }

        composable(NavHelper.DepositScreen.route){
            DepositScreen(navController)
        }
        composable(NavHelper.AirtimeScreen.route){
           AirtimeScreen(navController)
        }

        composable(NavHelper.DataScreen.route){
            DataScreen(navController)
        }
        composable(NavHelper.SubscriptionScreen.route){
            SubscriptionScreen(navController)
        }

        composable(NavHelper.GoalsScreen.route){
            GoalsScreen(navController)
        }
        composable(NavHelper.SavingsScreen.route){
            SavingsScreen(navController)
        }

        composable(NavHelper.StoryScreen.route){
            StoryScreen(navController)
        }

        composable(NavHelper.SupportScreen.route){
            SupportScreen(navController)
        }

        composable(NavHelper.HistoryScreen.route){
            HistoryScreen(navController)
        }

        composable(NavHelper.CardScreen.route){
            CardScreen(navController)
        }
        composable(NavHelper.ProfileScreen.route){
            ProfileScreen(navController)
        }
        composable(NavHelper.MessageScreen.route){
            MessageScreen(navController)
        }

        composable(NavHelper.SettingsScreen.route){
            SettingsScreen(navController)
        }
    }
}