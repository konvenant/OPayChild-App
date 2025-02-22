package com.example.opaychild.presentations.ui.graphs

sealed class NavHelper (var route: String) {
    object SplashScreen: NavHelper("splash_screen")
    object OnboardingScreen: NavHelper("onboardingScreen")
    object WelcomeScreen: NavHelper("welcomeScreen")
    object HomeScreen: NavHelper("homeScreen")
    object LoginScreen: NavHelper("loginScreen")
    object ConnectToParentScreen: NavHelper("connectScreen")
    object RegisterScreen: NavHelper("registerScreen")
    object RegisterUserDetailsScreen: NavHelper("RegisterUserDetailsScreen")
    object ResetPasswordScreen: NavHelper("ResetPasswordScreen")
    object VerifyScreen: NavHelper("verifyScreen")
    object RegisterChoiceScreen: NavHelper("registerChoiceScreen")
    object DashboardScreen: NavHelper("dashboardScreen")
    object RewardScreen: NavHelper("rewardScreen")
    object FriendsScreen: NavHelper("friendsScreen")
    object SettingsScreen: NavHelper("settingsScreen")
    object StoryScreen: NavHelper("storyScreen")
    object TransactionsScreen: NavHelper("transactionsScreen")
    object TransactionReceiptScreen: NavHelper("transactionReceiptScreen")
    object TransferToOPayScreen: NavHelper("transferToOPayScreen")
    object TransferToBankScreen: NavHelper("transferToBankScreen")
    object MakePaymentScreen: NavHelper("makePaymentScreen")
    object TransferToFriendScreen: NavHelper("transferToFriendScreen")
    object AirtimeScreen: NavHelper("airtimeScreen")
    object DataScreen: NavHelper("dataScreen")
    object DepositScreen: NavHelper("depositScreen")
    object WithdrawScreen: NavHelper("withdrawalScreen")
    object SubscriptionScreen: NavHelper("SubscriptionScreen")
    object GoalsScreen: NavHelper("GoalsScreen")
    object SavingsScreen: NavHelper("SavingsScreen")
    object SupportScreen: NavHelper("SupportScreen")
    object MessageScreen: NavHelper("MessageScreen")
    object CardScreen: NavHelper("CardScreen")
    object ProfileScreen: NavHelper("ProfileScreen")
    object HistoryScreen: NavHelper("HistoryScreen")

}