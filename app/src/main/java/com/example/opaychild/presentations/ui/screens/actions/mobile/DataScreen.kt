package com.example.opaychild.presentations.ui.screens.actions.mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.screens.actions.transfer.AuthBottomSheet
import com.example.opaychild.presentations.ui.screens.actions.transfer.PaymentMethodBottomSheet
import com.example.opaychild.presentations.ui.screens.actions.transfer.PinDialog
import com.example.opaychild.presentations.ui.screens.actions.transfer.TransactionSuccessfulDialog
import com.example.opaychild.presentations.ui.utils.FontHolders
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Green20
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White

@Composable
fun DataScreen(
  navController: NavController
){
    var showPaymentMethodDialog by remember { mutableStateOf(false) }
    var showAuthDialog by remember { mutableStateOf(false) }
    var showPinDialog by remember { mutableStateOf(false) }
    var showStatusAlert by remember { mutableStateOf(false) }
    var amountToPay by remember { mutableIntStateOf(0) }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedNetworkProvider by remember { mutableStateOf("") }
    var selectedPaymentMethod by remember { mutableStateOf("Wallet Balance") }

    Box(
      modifier = Modifier
          .fillMaxSize()
          .background(White)
  ) {
      DataPurchaseScreen(
          navController
      ) { phone, networkProvider, dataPlan ->
          selectedNetworkProvider = networkProvider.toString()
          phoneNumber = phone
          amountToPay = dataPlan.amount.toInt()
          showPaymentMethodDialog = true
      }

        if (showPaymentMethodDialog) {
            PaymentMethodBottomSheet(
                amount = amountToPay.toString(),
                accountNumber = phoneNumber,
                accountName = selectedNetworkProvider ,
                selectedPaymentMethod = selectedPaymentMethod,
                onPaymentMethodChange = { selectedPaymentMethod = it },
                onConfirm = {
                    showPaymentMethodDialog = false
                    showAuthDialog = true
                },
                onDismiss = { showPaymentMethodDialog = false },
                firstDesc = "Phone Number",
                secondDesc = "Network Provider"
            )
        }

        if (showAuthDialog) {
            AuthBottomSheet(
                onConfirm = { showAuthDialog = false; showPinDialog = true },
                onDismiss = { showAuthDialog = false }
            )
        }

        if (showPinDialog){
            PinDialog(onConfirm = {
                showPinDialog = false
                showStatusAlert = true
            }) {
                showPinDialog = false
            }
        }

        if (showStatusAlert){
            TransactionSuccessfulDialog(
                onDismiss = { showStatusAlert = false },
                onShare = {

                }
            )
        }
  }
}



@Composable
fun DataPurchaseScreen(
    navController: NavController,
    onPay: (String, NetworkProvider, DataPlan) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var selectedNetwork by remember { mutableStateOf(NetworkProvider.MTN) }
    var selectedPlanDuration by remember { mutableStateOf(PlanDuration.HOT) }
    var selectedPlan by remember { mutableStateOf<DataPlan?>(null) }
    var showNetworkDropdown by remember { mutableStateOf(false) }
    var showRecentNumbersDropdown by remember { mutableStateOf(false) }

    val recentNumbers = listOf("08012345678", "07012345678", "09012345678")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ElevatedButton(
                onClick = {
                    navController.navigateUp()
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = White,
                    contentColor = Green
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
                text = "Buy Data",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                fontFamily = FontHolders.font1,
                color = Green
            )
        }




        // Phone Number TextField
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                showRecentNumbersDropdown = true
            },
            label = { Text("Phone Number") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Green
            ),
            singleLine = true
        )

        // Recent Phone Numbers Dropdown
        if (showRecentNumbersDropdown) {
          Box (
              modifier = Modifier
                  .background(White)
          ){
              DropdownMenu(
                  expanded = showRecentNumbersDropdown,
                  onDismissRequest = { showRecentNumbersDropdown = false }
              ) {
                  recentNumbers.forEach { number ->
                      DropdownMenuItem(onClick = {
                          phoneNumber = number
                          showRecentNumbersDropdown = false
                      }, text = {
                          Text(text = number)
                      })
                  }
              }
          }
        }

        // Network Provider Dropdown
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Network Provider:")
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                Button(
                    onClick = { showNetworkDropdown = true },
                    modifier = Modifier
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = selectedNetwork.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = selectedNetwork.name)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }

                DropdownMenu(
                    expanded = showNetworkDropdown,
                    onDismissRequest = { showNetworkDropdown = false }
                ) {
                    NetworkProvider.entries.forEach { provider ->
                        DropdownMenuItem(onClick = {
                            selectedNetwork = provider
                            showNetworkDropdown = false
                        }, text = {
                           Row(){
                               Image(
                                   painter = painterResource(id = provider.logo),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(24.dp)
                                       .clip(CircleShape)
                               )
                               Spacer(modifier = Modifier.width(8.dp))
                               Text(text = provider.name)
                           }
                        })
                    }
                }
            }
        }

        // Tabs for Plan Durations
        val planDurations = PlanDuration.entries.toTypedArray()
        ScrollableTabRow(
            selectedTabIndex = planDurations.indexOf(selectedPlanDuration),
            edgePadding = 0.dp
        ) {
            planDurations.forEachIndexed { index, duration ->
                Tab(
                    selected = selectedPlanDuration == duration,
                    onClick = { selectedPlanDuration = duration }
                ) {
                    Text(
                        text = duration.name,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Data Plans Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(dataPlans[selectedPlanDuration]!!) { plan ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.elevatedCardElevation(4.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable { selectedPlan = plan }
                        .background(if (selectedPlan == plan) Green20 else Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (selectedPlan == plan) Green20 else Color.White)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = plan.dataValue,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Blue
                        )
                        Text(text = plan.period, fontSize = 14.sp, color = Color.Gray)
                        Text(text = plan.amount, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pay Button
        Button(
            onClick = { selectedPlan?.let { onPay(phoneNumber, selectedNetwork, it) } },
            enabled = phoneNumber.isNotEmpty() && selectedPlan != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Pay")
        }
    }
}


data class DataPlan(
    val dataValue: String,
    val period: String,
    val amount: String
)

enum class PlanDuration {
    HOT, DAILY, WEEKLY, MONTHLY, TWO_MONTHS, THREE_MONTHS, SIX_MONTHS, YEARLY
}

val dataPlans = mapOf(
    PlanDuration.HOT to listOf(
        DataPlan("100MB", "1 Day", "1"),
        DataPlan("500MB", "1 Day", "2"),
        DataPlan("1GB", "1 Day", "3"),
        DataPlan("2GB", "1 Day", "4"),
        DataPlan("3GB", "1 Day", "5"),
        DataPlan("5GB", "1 Day", "6")
    ),
    PlanDuration.DAILY to listOf(
        DataPlan("100MB", "1 Day", "1"),
        DataPlan("500MB", "1 Day", "2"),
        DataPlan("1GB", "1 Day", "3"),
        DataPlan("2GB", "1 Day", "4"),
        DataPlan("3GB", "1 Day", "5"),
        DataPlan("5GB", "1 Day", "6")
    ),
    PlanDuration.WEEKLY to listOf(
        DataPlan("500MB", "7 Days", "3"),
        DataPlan("1GB", "7 Days", "5"),
        DataPlan("2GB", "7 Days", "7"),
        DataPlan("5GB", "7 Days", "10"),
        DataPlan("10GB", "7 Days", "15"),
        DataPlan("15GB", "7 Days", "20")
    ),
    PlanDuration.MONTHLY to listOf(
        DataPlan("1GB", "30 Days", "10"),
        DataPlan("2GB", "30 Days", "15"),
        DataPlan("5GB", "30 Days", "20"),
        DataPlan("10GB", "30 Days", "30"),
        DataPlan("20GB", "30 Days", "50"),
        DataPlan("50GB", "30 Days", "70")
    ),
    PlanDuration.TWO_MONTHS to listOf(
        DataPlan("2GB", "60 Days", "15"),
        DataPlan("5GB", "60 Days", "20"),
        DataPlan("10GB", "60 Days", "30"),
        DataPlan("20GB", "60 Days", "50"),
        DataPlan("50GB", "60 Days", "70"),
        DataPlan("100GB", "60 Days", "100")
    ),
    PlanDuration.THREE_MONTHS to listOf(
        DataPlan("5GB", "90 Days", "20"),
        DataPlan("10GB", "90 Days", "30"),
        DataPlan("20GB", "90 Days", "50"),
        DataPlan("50GB", "90 Days", "70"),
        DataPlan("100GB", "90 Days", "100"),
        DataPlan("200GB", "90 Days", "150")
    ),
    PlanDuration.SIX_MONTHS to listOf(
        DataPlan("10GB", "180 Days", "50"),
        DataPlan("20GB", "180 Days", "70"),
        DataPlan("50GB", "180 Days", "100"),
        DataPlan("100GB", "180 Days", "150"),
        DataPlan("200GB", "180 Days", "200"),
        DataPlan("500GB", "180 Days", "300")
    ),
    PlanDuration.YEARLY to listOf(
        DataPlan("20GB", "365 Days", "100"),
        DataPlan("50GB", "365 Days", "150"),
        DataPlan("100GB", "365 Days", "200"),
        DataPlan("200GB", "365 Days", "300"),
        DataPlan("500GB", "365 Days", "500"),
        DataPlan("1TB", "365 Days", "700")
    )
)


@Preview
@Composable
fun DataPrev(){
    DataScreen(rememberNavController())
}