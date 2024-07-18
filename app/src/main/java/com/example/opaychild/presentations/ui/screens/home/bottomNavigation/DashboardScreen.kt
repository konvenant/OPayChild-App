package com.example.opaychild.presentations.ui.screens.home.bottomNavigation

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DataUsage
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.HistoryToggleOff
import androidx.compose.material.icons.filled.HotelClass
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opaychild.R
import com.example.opaychild.data.models.TransactionType
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.screens.home.actions.getTransactionTextColor
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font3
import com.example.opaychild.presentations.ui.utils.FontHolders.font4
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.presentations.ui.utils.FontHolders.font6
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.DarkGreen
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Green20
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Pink30
import com.example.opaychild.ui.theme.Pink80
import com.example.opaychild.ui.theme.Purple80
import com.example.opaychild.ui.theme.PurpleGrey80
import com.example.opaychild.ui.theme.Red
import com.example.opaychild.ui.theme.Red30
import com.example.opaychild.ui.theme.White
import com.example.opaychild.ui.theme.Yellow
import com.example.opaychild.ui.theme.Yellow30

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    navController: NavController,
    mainNavController: NavController
){
  Box(
      modifier = Modifier
          .fillMaxSize()
          .background(White)
  ){
      LazyColumn (
          modifier = Modifier.fillMaxSize()
      ) {
          item {
              Column (
                  verticalArrangement = Arrangement.SpaceBetween,
                  horizontalAlignment = Alignment.CenterHorizontally,
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(top = 48.dp)
              ){
                  Box (
                      modifier = Modifier
                          .size(120.dp)
                          .clip(RoundedCornerShape(10.dp))
                  ) {
                      Image(
                          painter = painterResource(id = R.drawable.boy4),
                          contentDescription = "profile image",
                          modifier = Modifier
                              .fillMaxSize()
                              .clickable {
                                  mainNavController.navigate(NavHelper.ProfileScreen.route)
                              },
                          contentScale = ContentScale.Crop
                      )
                      Icon(
                          imageVector = Icons.Default.HotelClass,
                          contentDescription = null,
                          tint = Yellow,
                          modifier = Modifier
                              .align(Alignment.BottomEnd)
                              .padding(8.dp)
                      )
                  }
                  Text(
                      text = "Hi Konvenant",
                      fontSize = 28.sp,
                      fontFamily = font1,
                      color = Pink
                  )
              }
          }

          item {
              BankCard(
                  balance = 12000.00,
                  accountNumber = "1234567890",
                  currentSpending = 1233.00,
                  maxSpending = 16000.00,
                  mainNavController
              )
          }

          item {
              Column {
                  Text(
                      text = "History",
                      fontFamily = font1,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.padding(start = 8.dp),
                      color = Blue
                  )
                  LazyRow (modifier = Modifier
                      .fillMaxWidth()
                      .padding(16.dp)) {
                      items(createDummyHistoryList()){
                          HistoryItemRow(
                              amount = it.amount,
                              type = it.type,
                              image = it.image,
                              icon = it.icon
                          ){
                              mainNavController.navigate(NavHelper.TransactionReceiptScreen.route)
                          }
                      }
                  }
              }
          }

          item {
              OptionsGrid { index ->
                  when(index){
                      0 -> {
                          mainNavController.navigate(NavHelper.TransferToBankScreen.route)
                      }
                      1 -> {
                          mainNavController.navigate(NavHelper.TransferToFriendScreen.route)
                      }
                      2 -> {
                          mainNavController.navigate(NavHelper.TransferToOPayScreen.route)
                      }
                      3 -> {
                          mainNavController.navigate(NavHelper.WithdrawScreen.route)
                      }
                      4 -> {
                          mainNavController.navigate(NavHelper.DepositScreen.route)
                      }
                      5 -> {
                          mainNavController.navigate(NavHelper.AirtimeScreen.route)
                      }
                      6 -> {
                          mainNavController.navigate(NavHelper.DataScreen.route)
                      }
                      7 -> {
                          mainNavController.navigate(NavHelper.SubscriptionScreen.route)
                      }
                      8 -> {
                          mainNavController.navigate(NavHelper.GoalsScreen.route)
                      }
                      9 -> {
                          mainNavController.navigate(NavHelper.SavingsScreen.route)
                      }
                      10 ->{
                          mainNavController.navigate(NavHelper.StoryScreen.route)
                      }
                      11 -> {
                          mainNavController.navigate(NavHelper.SupportScreen.route)
                      }

                  }
              }
          }
      }

      Row (
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
              .fillMaxWidth(0.4f)
              .align(Alignment.TopEnd)
              .padding(16.dp)
      ) {
          Icon(
              imageVector = Icons.Default.SupportAgent,
              contentDescription = "Support",
              tint = DarkGreen,
              modifier = Modifier.clickable {
                  mainNavController.navigate(NavHelper.SupportScreen.route)
              }

          )

          Icon(
              imageVector = Icons.Default.History,
              contentDescription = "History",
              tint = Pink,
              modifier = Modifier.clickable {
                  mainNavController.navigate(NavHelper.TransactionsScreen.route)
              }
          )
         BadgedBox(badge = {
             Box(modifier = Modifier
                 .background(Red, CircleShape)
                 .clip(CircleShape)
                 .size(8.dp)
             )


         } ) {
             Icon(
                 imageVector = Icons.Default.Notifications,
                 contentDescription = "Notifications",
                 tint = Blue,
                 modifier = Modifier.clickable {
                     mainNavController.navigate(NavHelper.HistoryScreen.route)
                 }
             )
         }

      }
  }
}


@Composable
fun BankCard(
    balance: Double,
    accountNumber: String,
    currentSpending: Double,
    maxSpending: Double,
    mainNavController: NavController
) {
    var isBalanceVisible by remember { mutableStateOf(true) }

    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Green
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Your Balance",
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = if (isBalanceVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Toggle Balance Visibility",
                    tint = White,
                    modifier = Modifier.clickable { isBalanceVisible = !isBalanceVisible }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isBalanceVisible) "$$balance" else "****",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                TextButton(onClick = {
                    mainNavController.navigate(NavHelper.DepositScreen.route)
                }) {
                    Text(text = "+ Add money", color = White)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Account Number",
                fontSize = 14.sp,
                color = Color.White
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
            Text(
                text = accountNumber,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .clickable {
                            Toast.makeText( context ,"Account Number Copied",Toast.LENGTH_SHORT).show()
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Current Spending",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = "$currentSpending / $maxSpending",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            LinearProgressIndicator(
                color = Yellow,
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth(),
                trackColor = Color.Gray,
                strokeCap = StrokeCap.Round,
                progress = (currentSpending / maxSpending).toFloat()
            )

            Spacer(modifier = Modifier.height(16.dp))
//
//            Image(
//                painter = painterResource(id = R.drawable.ic_mastercard),
//                contentDescription = "Card Type",
//                modifier = Modifier
//                    .size(48.dp)
//                    .align(Alignment.End)
//            )
        }
    }
}

@Composable
fun HistoryItemRow(
    icon: ImageVector? = null,
    image: Int? = null,
    amount: Double,
    type: TransactionType,
    onClick: () -> Unit
){
    val backgroundColor = when(type){
        TransactionType.TRANSFER -> Red30
        TransactionType.WITHDRAWAL -> Green20
        TransactionType.SAVINGS -> Pink30
        TransactionType.DEPOSIT -> Yellow30
        TransactionType.GOAL -> Purple80
        TransactionType.AIRTIME -> PurpleGrey80
        TransactionType.DATA -> Pink80
        TransactionType.SUBSCRIPTION -> Green20
    }



    val addOrMinus = when(type){
        TransactionType.TRANSFER -> "-"
        TransactionType.WITHDRAWAL ->"-"
        TransactionType.SAVINGS ->   "+"
        TransactionType.DEPOSIT -> "+"
        TransactionType.GOAL -> "+"
        TransactionType.AIRTIME -> "-"
        TransactionType.DATA -> "-"
        TransactionType.SUBSCRIPTION -> "-"
    }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .height(40.dp)
            .clickable {
                onClick()
            }
    ) {
       if (icon != null){
           Icon(
               imageVector = icon,
               contentDescription = null,
               tint = getTransactionTextColor(type),
               modifier = Modifier.padding( 8.dp)
           )
       } else{
           if (image != null){
               Image(
                   painter = painterResource(image),
                   contentDescription = null,
                   modifier = Modifier
                       .padding(8.dp)
                       .clip(CircleShape)
                       .size(25.dp)

               )
           } else {
               Icon(
                   imageVector = Icons.Default.HistoryToggleOff,
                   contentDescription = null,
                   tint = getTransactionTextColor(type),
                   modifier = Modifier.padding(vertical = 8.dp)
               )
           }
       }
        Text(
            text = "$addOrMinus$$amount",
            color = getTransactionTextColor(type),
            modifier = Modifier.padding( 8.dp),
            fontFamily = font1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun OptionsGrid(
    onClick: (Int) -> Unit
) {
    val options = listOf(
        Option("Transfer to Bank", R.drawable.icbank2,0),
        Option("Transfer to Friend", R.drawable.icfriend_selected,1),
        Option("Transfer to OPay", R.drawable.logo,2),
        Option("Withdraw", R.drawable.ic_withdraw,3),
        Option("Deposit", R.drawable.ic_deposit,4),
        Option("Airtime", R.drawable.ic_airtime,5),
        Option("Data", R.drawable.ic_data,6),
        Option("Subscription", R.drawable.ic_sub,7),
        Option("Goals", R.drawable.icmoney2,8),
        Option("Savings", R.drawable.ic_savings_selected,9),
        Option("Story", R.drawable.ic_story_selected,10),
        Option("Support", R.drawable.ic_support,11)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 64.dp)
           ,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quick Actions",
            fontFamily = font1,
            fontWeight = FontWeight.Bold,
            color = Blue
        )
        options.chunked(3).forEach { rowOptions ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                   ,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowOptions.forEach { option ->
                    OptionItem(option){
                        onClick(option.index)
                    }
                }
            }
        }
    }
}

@Composable
fun OptionItem(
    option: Option,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                onClick()
            }
    ) {
        Surface(
            shape = CircleShape,
            color = Green20,
            modifier = Modifier.size(64.dp)
        ) {
            Image(
                painter = painterResource(option.iconRes),
                contentDescription = option.label,
                modifier = Modifier.padding(16.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = option.label,
            fontSize = 11.sp,
            color = Pink,
            fontFamily = font1,
        )
    }
}

data class Option(
    val label: String,
    val iconRes: Int,
    val index: Int
)

fun createDummyHistoryList(): List<HistoryItem> {
    return listOf(
        HistoryItem(
            image = R.drawable.profile,
            amount = 500.0,
            type = TransactionType.TRANSFER
        ),
        HistoryItem(
            icon = Icons.Default.MoneyOff,
            amount = 200.0,
            type = TransactionType.WITHDRAWAL
        ),
        HistoryItem(
            icon = Icons.Default.Savings,
            amount = 1000.0,
            type = TransactionType.SAVINGS
        ),
        HistoryItem(
            icon = Icons.Default.AccountBalanceWallet,
            amount = 300.0,
            type = TransactionType.DEPOSIT
        ),
        HistoryItem(
            icon = Icons.Default.Flag,
            amount = 50.0,
            type = TransactionType.GOAL
        ),
        HistoryItem(
            icon = Icons.Default.Phone,
            amount = 20.0,
            type = TransactionType.AIRTIME
        ),
        HistoryItem(
            icon = Icons.Default.DataUsage,
            amount = 15.0,
            type = TransactionType.DATA
        ),
        HistoryItem(
            icon = Icons.Default.Subscriptions,
            amount = 30.0,
            type = TransactionType.SUBSCRIPTION
        ),

    )
}


fun createDummyHistoryListForFriend(
    image: Int
): List<HistoryItem> {
    return listOf(
        HistoryItem(
            image = image,
            amount = 500.0,
            type = TransactionType.TRANSFER
        ),
        HistoryItem(
            image = image,
            amount = 200.0,
            type = TransactionType.DEPOSIT
        ),
        HistoryItem(
            image = image,
            amount = 1000.0,
            type = TransactionType.TRANSFER
        ),
        HistoryItem(
            image = image,
            amount = 300.0,
            type = TransactionType.DEPOSIT
        ),
        HistoryItem(
            image = image,
            amount = 50.0,
            type = TransactionType.TRANSFER
        ),
        HistoryItem(
            image = image,
            amount = 20.0,
            type = TransactionType.TRANSFER
        ),
        HistoryItem(
            image = image,
            amount = 15.0,
            type = TransactionType.TRANSFER
        ),
        HistoryItem(
            image = image,
            amount = 30.0,
            type = TransactionType.TRANSFER
        ),

        )
}

@Preview
@Composable
fun DashPrev(){
    DashboardScreen(rememberNavController(),rememberNavController())
}


data class HistoryItem(
    val icon: ImageVector? = null,
    val image: Int? = null,
    val amount: Double,
    val type: TransactionType
)
