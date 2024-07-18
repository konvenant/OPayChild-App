package com.example.opaychild.presentations.ui.screens.home.actions

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
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
import com.example.opaychild.presentations.ui.utils.FontHolders
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font3
import com.example.opaychild.presentations.ui.utils.FontHolders.font4
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.ui.theme.Blue
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
fun TransactionHistoryScreen(
    navController: NavController
){
val  transactions = listOf(
    Transactions("Transfer to John", "12345", System.currentTimeMillis(), Status.SUCCESSFUL, TransactionType.TRANSFER),
    Transactions("Deposit from ATM", "54321", System.currentTimeMillis(), Status.FAILED, TransactionType.DEPOSIT),
    Transactions("Airtime Purchase", "67890", System.currentTimeMillis(), Status.PENDING, TransactionType.AIRTIME)
)
    TransactionHistoryList(transactions,navController )
}


@Composable
fun TransactionHistoryList(
    transactions: List<Transactions>,
    navController: NavController
) {
    Column(
        modifier = Modifier
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
                text = "Transaction History",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                color = Pink,
                fontFamily = font1
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "All Categories",
                        color = Green20
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Green20
                    )
                }

                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "All Status",
                        color = Green20
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Green20
                    )
                }
            }

            Row (
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Jul",
                        color = Blue
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Blue
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "IN $10,000",
                    color = DarkGreen,
                    fontFamily = font5,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Out $1,000",
                    color = Red,
                    fontFamily = font5,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(transactions) { transaction ->
                TransactionItem(transaction) {
                    navController.navigate(NavHelper.TransactionReceiptScreen.route)
                    // Handle transaction click based on type
//                    when (transaction.type) {
//                        TransactionType.TRANSFER -> {/* Navigate to Transfer details */}
//                        TransactionType.WITHDRAWAL -> {/* Navigate to Withdrawal details */}
//                        TransactionType.SAVINGS -> {/* Navigate to Savings details */}
//                        TransactionType.DEPOSIT -> {/* Navigate to Deposit details */}
//                        TransactionType.GOAL -> {/* Navigate to Goal details */}
//                        TransactionType.AIRTIME -> {/* Navigate to Airtime details */}
//                        TransactionType.DATA -> {/* Navigate to Data details */}
//                        TransactionType.SUBSCRIPTION -> {/* Navigate to Subscription details */}
//                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transactions, onClick: () -> Unit) {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    val date = remember { dateFormat.format(Date(transaction.date)) }

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(Pink30.copy(alpha = 0.1f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = getTransactionIcon(transaction.type),
            contentDescription = transaction.type.name,
            modifier = Modifier.size(40.dp),
            tint = getTransactionTextColor(transaction.type)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.desc,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = date,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        StatusIndicator(transaction.status)
    }
}

@Composable
fun StatusIndicator(status: Status) {
    val color = when (status) {
        Status.SUCCESSFUL -> Color.Green
        Status.FAILED -> Color.Red
        Status.PENDING -> Color.Yellow
    }
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color, shape = CircleShape)
    )
}

fun getTransactionIcon(type: TransactionType): ImageVector {
    return when (type) {
        TransactionType.TRANSFER -> Icons.Default.AttachMoney
        TransactionType.WITHDRAWAL -> Icons.Default.Money
        TransactionType.SAVINGS -> Icons.Default.Savings
        TransactionType.DEPOSIT -> Icons.Default.AddCard
        TransactionType.GOAL -> Icons.Default.Games
        TransactionType.AIRTIME -> Icons.Default.PhoneAndroid
        TransactionType.DATA -> Icons.Default.Wifi
        TransactionType.SUBSCRIPTION -> Icons.Default.Subscriptions
    }
}


fun getTransactionTextColor(type: TransactionType) : Color {
    return when(type){
        TransactionType.TRANSFER -> Red
        TransactionType.WITHDRAWAL -> Green
        TransactionType.SAVINGS -> Pink
        TransactionType.DEPOSIT -> Yellow
        TransactionType.GOAL -> Blue
        TransactionType.AIRTIME -> Blue
        TransactionType.DATA -> Pink
        TransactionType.SUBSCRIPTION -> Green
    }
}

@Preview
@Composable
fun TransHisPrev(){
    TransactionHistoryScreen(rememberNavController())
}