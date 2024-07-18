package com.example.opaychild.presentations.ui.screens.actions.transfer

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.utils.FontHolders
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Green20
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Pink30
import com.example.opaychild.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConfirmTransferScreen(
    navController: NavController
){
    var amount by remember { mutableStateOf("") }
    var remark by remember { mutableStateOf("") }
    var showPaymentMethodDialog by remember { mutableStateOf(false) }
    var showAuthDialog by remember { mutableStateOf(false) }
    var showPinDialog by remember { mutableStateOf(false) }
    var showStatusAlert by remember { mutableStateOf(false) }
    var selectedPaymentMethod by remember { mutableStateOf("Wallet Balance") }
    val accountNumber = remember { mutableStateOf("1234567890") }
    val accountName by remember { mutableStateOf("John Doe") }
  Box (
      modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
  ) {
      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
              .background(White)
              .fillMaxSize()
              .padding(16.dp)
      ) {


          Spacer(modifier = Modifier.height(66.dp))

          Column (
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.SpaceBetween,
              modifier = Modifier.padding(8.dp)
          ){
              Image(
                  painter = painterResource(id = R.drawable.logo),
                  contentDescription = null,
                  modifier = Modifier
                      .size(80.dp)
                      .border(1.dp, Pink, CircleShape)
              )

              Text(
                  text = accountName,
                  fontWeight = FontWeight.SemiBold,
                  color = Blue,
                  fontFamily = font2,
                  fontSize = 20.sp
              )
              Text(text = " OPay ${accountNumber.value.toString()}")

          }
          Spacer(modifier = Modifier.height(16.dp))

          OutlinedTextField(
              value = amount,
              onValueChange = {
                  amount = it
              },
              label = { Text("Amount") },
              modifier = Modifier.fillMaxWidth(),
              colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = Pink,
                  unfocusedBorderColor = Green
              ),
              singleLine = true,
              keyboardOptions = KeyboardOptions(
                  keyboardType = KeyboardType.NumberPassword
              ),
              placeholder = {Text("100-40,000")},
              leadingIcon = {
                  Icon(
                      imageVector = Icons.Default.CurrencyBitcoin,
                      contentDescription = null,
                      tint = Color.Black
                  )
              }
          )

          Spacer(modifier = Modifier.height(16.dp))

          OutlinedTextField(
              value = remark,
              onValueChange = {
                  remark = it
              },
              label = { Text("Remark") },
              modifier = Modifier.fillMaxWidth(),
              colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = Pink,
                  unfocusedBorderColor = Green
              ),
              singleLine = true,
              placeholder = {Text("What is this for? (optional)")},

              )

          Spacer(modifier = Modifier.height(32.dp))

          ElevatedButton(
              onClick = {
                  showPaymentMethodDialog = true
              },
              colors = ButtonDefaults.elevatedButtonColors(
                  containerColor = Green,
                  contentColor = White,
                  disabledContainerColor = Green20,
                  disabledContentColor = White
              ),
              elevation = ButtonDefaults.elevatedButtonElevation(6.dp),
              modifier = Modifier.fillMaxWidth(0.8f),
              enabled = amount.isNotEmpty()
          ) {
              Text(
                  "Confirm",
                  modifier = Modifier.padding(vertical = 8.dp),
                  fontSize = 19.sp,
                  fontWeight = FontWeight.Bold

              )
          }
      }

      Row (
          modifier = Modifier
              .fillMaxWidth()
              .align(Alignment.TopCenter)
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
              text = "Make Transfer",
              fontSize = 24.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier
                  .padding(16.dp)
                  ,
              fontFamily = FontHolders.font1,
              color = Pink
          )
      }

  }
    if (showPaymentMethodDialog) {
        PaymentMethodBottomSheet(
            amount = amount,
            accountNumber = accountNumber.value,
            accountName = accountName,
            selectedPaymentMethod = selectedPaymentMethod,
            onPaymentMethodChange = { selectedPaymentMethod = it },
            onConfirm = {
                showPaymentMethodDialog = false
                showAuthDialog = true
            },
            onDismiss = { showPaymentMethodDialog = false },
            firstDesc = "Account Number",
            secondDesc = "Account Name"
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodBottomSheet(
    amount: String,
    accountNumber: String,
    accountName: String,
    selectedPaymentMethod: String,
    onPaymentMethodChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    firstDesc: String,
    secondDesc: String
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Payment Details", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Amount: N $amount",
                    fontSize = 20.sp,
                    color = Blue,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font2
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "$firstDesc: $accountNumber")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$secondDesc: $accountName")

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Payment Method", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Wallet Balance",
                        modifier = Modifier
                            .clickable { onPaymentMethodChange("Wallet Balance") }
                            .background(
                                if (selectedPaymentMethod == "Wallet Balance") Pink else Pink30,
                                RoundedCornerShape(10.dp)
                            )
                            .padding(16.dp),
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                    Text(
                        text = "Savings Balance",
                        modifier = Modifier
                            .clickable { onPaymentMethodChange("Savings Balance") }
                            .background(
                                if (selectedPaymentMethod == "Savings Balance") Pink else Pink30,
                                RoundedCornerShape(10.dp)
                            )
                            .padding(16.dp),
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green
                    )
                ) {
                    Text(text = "Pay")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthBottomSheet(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(bottom = 32.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Authentication", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Please authenticate to continue")

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onConfirm,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green
                        )
                    ) {
                        Text(text = "Use PIN")
                    }

                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Fingerprint, contentDescription = "Use Fingerprint")
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var pin by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter PIN",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 1..4) {
                        OutlinedTextField(
                            value = if (pin.length >= i) pin[i - 1].toString() else "",
                            onValueChange = {
                                if (it.length == 1 && pin.length < 4) {
                                    pin += it
                                } else if (it.isEmpty() && pin.isNotEmpty()) {
                                    pin = pin.dropLast(1)
                                }
                            },
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier
                                .width(50.dp)
                                .background(Color.Transparent),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 24.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Green,
                                unfocusedBorderColor = Green20
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onConfirm(pin) },
                    enabled = pin.length == 4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green
                    )
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}


@Composable
fun TransactionSuccessfulDialog(
    onDismiss: () -> Unit,
    onShare: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            tonalElevation = 6.dp,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Green.copy(alpha = 0.2f), shape = CircleShape)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Transaction Successful",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Your transaction has been completed successfully.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Button(
                    onClick = onShare,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green
                    )
                ) {
                    Text(text = "Share Receipt")
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onDismiss) {
                    Text(text = "Close", color = Color.Red)
                }
            }
        }
    }
}

@Preview
@Composable
fun CTSPrev(){
    ConfirmTransferScreen(rememberNavController())
}



