package com.example.opaychild.presentations.ui.screens.home.user

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.outlined.AddBusiness
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opaychild.R
import com.example.opaychild.data.models.Status
import com.example.opaychild.data.models.Tier
import com.example.opaychild.data.models.TransactionType
import com.example.opaychild.data.models.Transactions
import com.example.opaychild.data.models.User
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.screens.actions.transaction.SubscriptionScreen
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font2
import com.example.opaychild.presentations.ui.utils.FontHolders.font3
import com.example.opaychild.presentations.ui.utils.FontHolders.font4
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Cream
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

@Composable
fun ProfileScreen (
    navController: NavController
) {
    val user = User(
        id = "1",
        accountNumber = 1234567890,
        accountName = "John Doe",
        verified = true,
        password = "password",
        pin = 1234,
        dob = "01/01/2010",
        age = "14",
        connectedToParent = true,
        parentAccountNumber = 987654321,
        createdAt = System.currentTimeMillis(),
        lastModified = System.currentTimeMillis(),
        accountBalance = 1000.0,
        tier = Tier.DREAMERS,
        displayName = "Johnny",
        phoneNumber = 1234567890,
        email = "john.doe@example.com",
        favoriteColor = "Blue",
        friends = listOf()
    )

    Profile(
        navController,
        user = user,
        onUpdate = { updatedUser ->
            // Handle user update
            println("Updated user: $updatedUser")
        }
    )
}

@Composable
fun Profile(
    navController: NavController,
    user: User,
    onUpdate: (User) -> Unit
) {
    var displayName by remember { mutableStateOf(user.displayName) }
    var phoneNumber by remember { mutableStateOf(user.phoneNumber?.toString() ?: "") }
    var email by remember { mutableStateOf(user.email ?: "") }
    var favoriteColor by remember { mutableStateOf(user.favoriteColor) }

    val transition = rememberInfiniteTransition()
    val animatedScale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
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
                    contentColor = Color.Green
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIos,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            Text(
                "Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                fontFamily = font1,
            )
        }


        Box(
            modifier = Modifier
                .size(120.dp)
                .scale(animatedScale)
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            user.image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            } ?: Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Profile Image",
                tint = Color.Gray,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Account Number: ${user.accountNumber}",
            fontSize = 16.sp,
            fontFamily = font2,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (user.verified) {
            Text(
                "Verified",
                color = Color.Green,
                fontSize = 16.sp,
                fontFamily = font2,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        ProfileDetailItem(label = "Display Name", value = displayName) {
            displayName = it
        }
        ProfileDetailItem(label = "Phone Number", value = phoneNumber) {
            phoneNumber = it
        }
        ProfileDetailItem(label = "Email", value = email) {
            email = it
        }
        ProfileDetailItem(label = "Favorite Color", value = favoriteColor) {
            favoriteColor = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        val buttonElevation by animateDpAsState(
            targetValue = if (displayName.isNotBlank() && phoneNumber.isNotBlank() && email.isNotBlank() && favoriteColor.isNotBlank()) 8.dp else 0.dp,
            animationSpec = tween(500)
        )

        ElevatedButton(
            onClick = {
                val updatedUser = user.copy(
                    displayName = displayName,
                    phoneNumber = phoneNumber.toIntOrNull(),
                    email = email,
                    favoriteColor = favoriteColor,
                    lastModified = System.currentTimeMillis()
                )
                onUpdate(updatedUser)
            },
            modifier = Modifier.fillMaxWidth(),
            elevation = ButtonDefaults.elevatedButtonElevation(buttonElevation)
        ) {
            Text("Update")
        }
    }
}

@Composable
private fun ProfileDetailItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            "$label:",
            fontSize = 16.sp,
            fontFamily = font2,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun ProfilePrev(){
    ProfileScreen(rememberNavController())
}