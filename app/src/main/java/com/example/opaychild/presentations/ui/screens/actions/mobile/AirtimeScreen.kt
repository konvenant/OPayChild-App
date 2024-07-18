package com.example.opaychild.presentations.ui.screens.actions.mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Pink30
import com.example.opaychild.ui.theme.White


@Composable
fun AirtimeScreen(
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


   Box (
       modifier = Modifier
           .fillMaxSize()
           .background(White)
   ) {
       AirtimeItem(onPay = { phone, selectedNetwork, amount ->
           amountToPay = amount
           showPaymentMethodDialog = true
           phoneNumber = phone
           selectedNetworkProvider = selectedNetwork.toString()
       } ,navController = navController)

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
fun AirtimeItem(
    onPay: (String, NetworkProvider, Int) -> Unit,
    navController: NavController
) {
    var phoneNumber by remember { mutableStateOf("") }
    var selectedNetwork by remember { mutableStateOf(NetworkProvider.MTN) }
    var amount by remember { mutableIntStateOf(0) }
    var showNetworkDropdown by remember { mutableStateOf(false) }
    var showRecentNumbersDropdown by remember { mutableStateOf(false) }

    val recommendedAmounts = listOf(50, 100, 200, 500, 1000, 2000)
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
                text = "Buy Airtime",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                fontFamily = font1,
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
           ) {
               DropdownMenu(
                   expanded = showRecentNumbersDropdown,
                   onDismissRequest = { showRecentNumbersDropdown = false }
               ) {
                   recentNumbers.forEach { number ->
                       DropdownMenuItem(
                           onClick = {
                               phoneNumber = number
                               showRecentNumbersDropdown = false
                           },
                           text = {
                               Text(text = number)
                           }
                       )
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
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Pink
                    )
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
                        },
                            text = {
                              Row {
                                  Image(
                                      painter = painterResource(id = provider.logo),
                                      contentDescription = null,
                                      modifier = Modifier
                                          .size(24.dp)
                                          .clip(CircleShape)
                                  )
                                  Spacer(modifier = Modifier.width(16.dp))
                                  Text(text = provider.name)
                              }
                            })
                    }
                }
            }
        }

        // Amount TextField
        OutlinedTextField(
            value = amount.toString(),
            onValueChange = {
                             amount = it.toInt()
            },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Green
            ),
            singleLine = true
        )

        // Recommended Amounts
        Text(
            text = "Recommended Amounts:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(recommendedAmounts.size) { index ->
                Button(
                    onClick = { amount = recommendedAmounts[index] },
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Pink30
                    )
                ) {
                    Text(
                        text = recommendedAmounts[index].toString(),
                        fontSize = 18.sp,
                        fontFamily = font2
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pay Button
        Button(
            onClick = { onPay(phoneNumber, selectedNetwork, amount) },
            enabled = phoneNumber.isNotEmpty() && amount.toString().isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            )
        ) {
            Text(text = "Pay")
        }
    }
}



enum class NetworkProvider(val logo: Int) {
    MTN(R.drawable.mtn),
    Airtel(R.drawable.airtel),
    Mobile9(R.drawable.nine_mobile),
    Glo(R.drawable.glo)
}

@Preview
@Composable
fun AirtimePrev(){
    AirtimeScreen(rememberNavController())
}