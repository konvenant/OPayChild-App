package com.example.opaychild.presentations.ui.screens.actions.transfer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Green20
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferToOPayScreen(
    navController: NavController
){
    val recentAccounts = listOf(
        RecentAccount("John Doe", "1234567890", Bank("Bank A", R.drawable.boy4)),
        RecentAccount("Jane Smith", "0987654321", Bank("Bank B", R.drawable.girl2))
    )

    TransferToOPayAccountScreen( recentAccounts, navController  )
}

@Composable
fun TransferToOPayAccountScreen(
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
                text = "Transfer to OPay Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontHolders.font1,
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
            label = { Text("Phone No./OPay Account No./ Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Green
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.QrCode2,
                    contentDescription = null,
                    tint = Green20,
                    modifier = Modifier.clickable {

                    }
                )
            },
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

data class RecentOPayAccount(
    val name: String,
    val accountNumber: String,
    val img : Int = R.drawable.profile
)

@Preview
@Composable
fun TransferToOPayPrev(){
    TransferToOPayScreen(rememberNavController())
}