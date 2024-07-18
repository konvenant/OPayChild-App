package com.example.opaychild.presentations.ui.screens.onboarding.auth

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White

@Composable
fun VerifyScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    val phoneNumber = ""
    var token by remember { mutableStateOf("") }
    val maxTokenLength = 4
    val tokenColors = List(maxTokenLength) { index ->
        animateColorAsState(
            targetValue = if (index < token.length) Green else Color.Gray, label = ""
        )
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
            painter = painterResource(id = R.drawable.verification),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )

        Text(text = "Verifying Phone Number: $phoneNumber")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 0 until maxTokenLength) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(tokenColors[i].value)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = if (i < token.length) token[i].toString() else "")
                }
                if (i < maxTokenLength - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = token,
            onValueChange = { value ->
                if (value.length <= maxTokenLength) {
                    token = value
                }
            },
            label = { Text("Enter 4-digit Code") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Green
            )
        )

        Spacer(modifier = Modifier.height(46.dp))

        Button(onClick = {
            // Handle token verification
            if (token.length == maxTokenLength) {
                navController.navigate(NavHelper.HomeScreen.route) {
                    popUpTo(NavHelper.VerifyScreen.route) {
                        inclusive = true
                    }
                }
            }
        },colors = ButtonDefaults.buttonColors(
            containerColor = Green
        ),
            modifier = Modifier.fillMaxWidth(0.7f),
            enabled = token.isNotEmpty()) {
            Text(text = "Continue", modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun VerifyScreenPrev(){

}