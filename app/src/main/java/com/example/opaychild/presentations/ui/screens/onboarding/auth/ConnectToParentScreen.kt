package com.example.opaychild.presentations.ui.screens.onboarding.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.presentations.ui.utils.FontHolders.font4
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.presentations.ui.utils.FontHolders.font6
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White

@Composable
fun ConnectToParentScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    var accountNumber by remember { mutableStateOf(TextFieldValue("")) }
    var accountName by remember { mutableStateOf<String?>(null) }
    var showCodeDialog by remember { mutableStateOf(false) }
    var verificationCode by remember { mutableStateOf("") }
    var customToken by remember { mutableStateOf("") }
    var showTokenField by remember { mutableStateOf(false) }

    // Mock function to check if account exists
    fun getAccountName(accountNumber: String): String? {
        return if (accountNumber == "1234567890") "Parent Account Name" else null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.connect),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Connect to Parent",
            fontSize = 32.sp,
            color = Blue,
            fontFamily = font1
        )
        Spacer(modifier = Modifier.height(36.dp))
        OutlinedTextField(
            value = accountNumber,
            onValueChange = { value ->
                accountNumber = value
                if (value.text.length == 10) {
                    accountName = getAccountName(value.text)
                }
            },
            label = { Text("Enter Parent Account Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Green
            )
        )

        if (accountName != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Account Name: $accountName", fontFamily = font2)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { showCodeDialog = true }, colors = ButtonDefaults.buttonColors(
                    containerColor = Pink
                    ),
                modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(text = "Continue",modifier = Modifier.padding(8.dp))
            }
        }

        if (showCodeDialog) {
            Dialog(onDismissRequest = { showCodeDialog = false }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "A 4-digit code has been sent to the parent account's notification and email.",
                            fontFamily = font5
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = verificationCode,
                            onValueChange = { verificationCode = it },
                            label = { Text("Enter 4-digit Code") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            // Mock verification code check
                            if (verificationCode == "1234") {
                                showCodeDialog = false
                                navController.navigate(NavHelper.RegisterUserDetailsScreen.route) {
                                    popUpTo(NavHelper.ConnectToParentScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Pink
                        ),
                            modifier = Modifier.fillMaxWidth(0.7f)) {
                            Text(text = "Verify", modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }


    }
}

@Preview
@Composable
fun ConnectToParentScreenPrev(){
}

