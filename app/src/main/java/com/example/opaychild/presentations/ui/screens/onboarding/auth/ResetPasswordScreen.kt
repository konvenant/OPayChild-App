package com.example.opaychild.presentations.ui.screens.onboarding.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ResetPasswordScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    var stage by remember { mutableIntStateOf(0) }
    var accountNumber by remember { mutableStateOf(TextFieldValue("")) }
    var token by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val maxTokenLength = 4

    // Mock function to verify account number
    fun verifyAccountNumber(accountNumber: String): Boolean {
        return accountNumber == "1234567890"
    }

    // Mock function to verify token
    fun verifyToken(token: String): Boolean {
        return token == "1234"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(targetState = stage, transitionSpec = {
            (fadeIn(animationSpec = tween(300)) + slideInVertically(initialOffsetY = { it })).togetherWith(
                fadeOut(animationSpec = tween(300)) + slideOutVertically(targetOffsetY = { -it })
            )
        }, label = "") { targetStage ->
            when (targetStage) {
                0 -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.forgot),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Reset Password",
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontFamily = font2
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        OutlinedTextField(
                            value = accountNumber,
                            onValueChange = { value ->
                                accountNumber = value
                            },
                            label = { Text("Enter Account Number") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Pink,
                                unfocusedBorderColor = Green
                            )
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        Button(onClick = {
                            if (verifyAccountNumber(accountNumber.text)) {
                                stage = 1
                            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green
                            ),
                            modifier = Modifier.fillMaxWidth(0.7f)) {
                            Text(
                                "Continue",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                1 -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Image(
                            painter = painterResource(id = R.drawable.verification),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                        )
                        Text("Verification Code", fontSize = 24.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("A 4-digit code has been sent to your phone number.")
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                            if (verifyToken(token)) {
                                stage = 2
                            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                    containerColor = Green
                                    )
                        ) {
                            Text("Continue")
                        }
                    }
                }
                2 -> {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.verification),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                        )
                        Text("New Password", fontSize = 24.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = newPassword,
                            onValueChange = { value ->
                                newPassword = value
                            },
                            label = { Text("Enter New Password") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Pink,
                            unfocusedBorderColor = Green
                        )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { value ->
                                confirmPassword = value
                            },
                            label = { Text("Confirm New Password") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Pink,
                                unfocusedBorderColor = Green
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            if (newPassword == confirmPassword) {
                                navController.navigate(NavHelper.LoginScreen.route) {
                                    popUpTo(NavHelper.ResetPasswordScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green
                            ),
                            ) {
                            Text("Finish", modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }

    }
}


@Preview
@Composable
fun ResetPasswordPrev(){

}