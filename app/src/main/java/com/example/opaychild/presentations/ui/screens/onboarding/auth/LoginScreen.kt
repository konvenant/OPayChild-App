package com.example.opaychild.presentations.ui.screens.onboarding.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.presentations.ui.utils.FontHolders.font3
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.DarkGreen
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Red
import com.example.opaychild.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController
) {
    var hasLoggedInBefore by remember { mutableStateOf(false) }
    var showAccountNumber by remember { mutableStateOf(!hasLoggedInBefore) }
    var accountNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRecording by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val size by infiniteTransition.animateFloat(
        initialValue = 20f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    LaunchedEffect(Unit) {
        // Simulate fetching login state
        delay(6000)
        hasLoggedInBefore = true // Assume the user has logged in before
        showAccountNumber = !hasLoggedInBefore
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Image
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Account Number Field (if applicable)
            AnimatedVisibility(
                visible = showAccountNumber,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                OutlinedTextField(
                    value = accountNumber,
                    onValueChange = { accountNumber = it },
                    label = { Text("Account Number") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Pink,
                        unfocusedBorderColor = Green
                    )
                )
            }

            AnimatedVisibility(
                visible = !showAccountNumber,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
              Text(
                  text = "Welcome Back !!, please login to continue greatness",
                  modifier = Modifier.padding(16.dp),
                  color = Green,
                  fontSize = 20.sp,
                  textAlign = TextAlign.Center,
                  fontFamily = font1
              )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Pink,
                    unfocusedBorderColor = Green
                )
            )



            Spacer(modifier = Modifier.height(120.dp))

            // Login Button
            ElevatedButton(
                onClick = {
                    navController.navigate(NavHelper.HomeScreen.route) {
                        popUpTo(NavHelper.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Pink,
                    contentColor = White
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(6.dp),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    "Login",
                    modifier = Modifier.padding(vertical = 8.dp),
                   fontSize = 19.sp,
                    fontWeight = FontWeight.Bold

                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Forgot Password? Reset!!",
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .clickable {
                        navController.navigate(NavHelper.ResetPasswordScreen.route)
                    },
                color = DarkGreen,
                fontFamily = font1
            )

            Spacer(modifier = Modifier.height(38.dp))

            // Fingerprint and Voice Recorder Icons
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /* Handle fingerprint authentication */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fingeprint),
                        contentDescription = "Fingerprint",
                        tint = Green
                    )
                }

               Column (
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   IconButton(onClick = { isRecording = !isRecording }) {
                       Icon(
                           painter = painterResource(id = if (isRecording) R.drawable.ic_stop else R.drawable.ic_mic),
                           contentDescription = "Voice Recorder",
                           tint = if (isRecording) Red else Pink,
                           modifier = if (isRecording) Modifier.size(size.dp) else Modifier.size(30.dp)
                       )
                   }

                   if (isRecording){
                       Text(
                           text = "Listening",
                           fontFamily = font5,
                           color = Blue
                       )
                   }
               }
            }

            Spacer(modifier = Modifier.height(38.dp))
            AnimatedVisibility(
                visible = !showAccountNumber,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                Text(
                    text = "Have another account?, Switch Account",
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .clickable {
                            showAccountNumber = !showAccountNumber
                        },
                    color = Blue,
                    fontFamily = font2
                )
            }

        }

        Text(
            text = "Yet to create an account, Register!!",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .clickable {
                    navController.navigate(NavHelper.RegisterChoiceScreen.route)
                },
            color = Pink,
            fontFamily = font3
        )

        Image(
            painter = painterResource(id = R.drawable.boy_waving_hand),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = 60.dp)
                .rotate(300f) // Rotate by 45 degrees
        )


    }
}

@Preview
@Composable
fun LoginScreenPrev(){
  LoginScreen(rememberNavController() )
}