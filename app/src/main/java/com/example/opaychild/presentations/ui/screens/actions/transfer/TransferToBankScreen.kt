package com.example.opaychild.presentations.ui.screens.actions.transfer

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opaychild.R
import com.example.opaychild.data.models.Status
import com.example.opaychild.data.models.TransactionType
import com.example.opaychild.data.models.Transactions
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.presentations.ui.utils.FontHolders.font3
import com.example.opaychild.presentations.ui.utils.FontHolders.font4
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Cream
import com.example.opaychild.ui.theme.DarkGreen
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Green20
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Pink30
import com.example.opaychild.ui.theme.Red
import com.example.opaychild.ui.theme.White
import com.example.opaychild.ui.theme.Yellow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferToBankScreen(
    navController: NavController
){
    val banks = listOf(
        Bank("Bank A", R.drawable.logo),
        Bank("Bank B", R.drawable.app_icon),
        Bank("Bank C", R.drawable.icbank2)
    )

    val recentAccounts = listOf(
        RecentAccount("John Doe", "1234567890", Bank("Bank A", R.drawable.logo)),
        RecentAccount("Jane Smith", "0987654321", Bank("Bank B", R.drawable.icbank2))
    )
    TransferToBankAccountScreen(banks,recentAccounts,navController)
}


@Composable
fun TransferToBankAccountScreen(
    banks: List<Bank>,
    recentAccounts: List<RecentAccount>,
    navController: NavController
) {
    var accountNumber by remember { mutableStateOf("") }
    var selectedBank by remember { mutableStateOf<Bank?>(null) }
    var accountName by remember { mutableStateOf<String?>(null) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showBanks by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .background(White)
        .fillMaxSize()
        .padding(16.dp)) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ElevatedButton(
                onClick = {
                    navController.navigateUp()
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = White,
                    contentColor = Pink
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(6.dp)
            ) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBackIos,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Transfer to Bank Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = font1,
                color = Pink
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = accountNumber,
            onValueChange = {
                accountNumber = it
                accountName = if (it.length == 10) {
                    // Simulate verifying account number
                    "John Doe"
                } else {
                    null
                }
            },
            label = { Text("Account Number") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Green
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (accountName != null) {
            Text(
                text = "Account Name: $accountName",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Green,
                modifier = Modifier.clickable {
                    navController.navigate(NavHelper.MakePaymentScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select Bank",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Blue,
            fontFamily = font2,
            modifier = Modifier.clickable {
                showBanks = true
            }
        )

        if (showBanks){

            LazyColumn(modifier = Modifier.height(200.dp)) {
                items(banks) { bank ->
                    BankItem(bank = bank, onClick = { selectedBank = bank })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                          navController.navigate(NavHelper.MakePaymentScreen.route)
                },
                enabled = accountName != null && selectedBank != null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Continue")
            }
        }



        Spacer(modifier = Modifier.height(8.dp))


        // Dummy real-time bank monitor
        Text(
            text = "Real-Time Bank Monitor",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = DarkGreen,
            fontFamily = font2
        )
        Spacer(modifier = Modifier.height(16.dp))
        RealTimeBankMonitor()

        Spacer(modifier = Modifier.height(16.dp))

        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Green,
            containerColor = White,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Recent Accounts")
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Favorites")
            }
        }

        when (selectedTabIndex) {
            0 -> RecentAccountsTab(recentAccounts){
                navController.navigate(NavHelper.MakePaymentScreen.route)
            }
            1 -> FavoritesTab(recentAccounts){
                navController.navigate(NavHelper.MakePaymentScreen.route)
            }
        }



    }
}

@Composable
fun BankItem(bank: Bank, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
            .background(Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = bank.logo),
            contentDescription = bank.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = bank.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun RealTimeBankMonitor() {
    // Dummy monitor for example purposes
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Cyan.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Bank Monitor Data", fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun RecentAccountsTab(
    recentAccounts: List<RecentAccount>,
    onClick: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(recentAccounts) { account ->
            RecentAccountItem(account = account){
                onClick()
            }
        }
    }
}

@Composable
fun FavoritesTab(
    favoriteAccounts: List<RecentAccount>,
                 onClick: () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(favoriteAccounts) { account ->
            RecentAccountItem(account = account){
                onClick()
            }
        }
    }
}

@Composable
fun RecentAccountItem(
    account: RecentAccount,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = account.bank.logo),
            contentDescription = account.bank.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = account.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Blue
            )
            Text(text = account.accountNumber, fontSize = 14.sp, color = Color.Gray)
        }
    }
}


data class Bank(
    val name: String,
    val logo: Int
)

data class RecentAccount(
    val name: String,
    val accountNumber: String,
    val bank: Bank
)


@Preview
@Composable
fun TransferToBankPrev(){
    TransferToBankScreen(rememberNavController())
}