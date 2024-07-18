package com.example.opaychild.presentations.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
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
import androidx.compose.ui.window.DialogProperties
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
fun CardScreen (
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

    Card(
        navController,
        user = user,
        onAddParentCard = { /* Handle add parent card */ },
        onRemoveParentCard = { /* Handle remove parent card */ },
        onUpgradeCard = { /* Handle upgrade card */ }
    )
}


@Composable
fun Card(
    navController: NavController,
    user: User,
    onAddParentCard: () -> Unit,
    onRemoveParentCard: () -> Unit,
    onUpgradeCard: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogContent by remember { mutableStateOf<@Composable () -> Unit>({}) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
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
                text = "Your Card",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Green,
                fontFamily = font1
            )
        }



        Spacer(modifier = Modifier.height(16.dp))

        // Card Design
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Green
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Parent Card",
                    color = Color.White,
                    fontSize = 16.sp
                )

                Text(
                    text = "**** **** **** 1234",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = user.accountName,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "VALID THRU 12/24",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_mastercard),
                        contentDescription = "MasterCard Logo",
                        modifier = Modifier.size(48.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ActionButton(
                text = "Add Parent Card",
                icon = R.drawable.ic_card,
                onClick = {
                    showDialog = true
                    dialogContent = {
                        AddParentCardDialog(onConfirm = {
                            onAddParentCard()
                            showDialog = false
                        }, onCancel = { showDialog = false })
                    }
                }
            )

            ActionButton(
                text = "Remove Parent Card",
                icon = R.drawable.ic_card_unselected,
                onClick = {
                    showDialog = true
                    dialogContent = {
                        RemoveParentCardDialog(onConfirm = {
                            onRemoveParentCard()
                            showDialog = false
                        }, onCancel = { showDialog = false })
                    }
                }
            )

            ActionButton(
                text = "Upgrade Card",
                icon = R.drawable.iccard2,
                onClick = {
                    showDialog = true
                    dialogContent = {
                        UpgradeCardDialog(onConfirm = {
                            onUpgradeCard()
                            showDialog = false
                        }, onCancel = { showDialog = false })
                    }
                }
            )
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }, properties = DialogProperties()) {
            dialogContent()
        }
    }
}

@Composable
fun ActionButton(text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = Color.White
        ),
        modifier = Modifier
            .height(48.dp)
            .padding(horizontal = 4.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun AddParentCardDialog(onConfirm: () -> Unit, onCancel: () -> Unit) {
    DialogContent(
        title = "Add Parent Card",
        confirmText = "Add",
        onConfirm = onConfirm,
        onCancel = onCancel
    ) {
        Text("Are you sure you want to add a parent card?")
    }
}

@Composable
fun RemoveParentCardDialog(onConfirm: () -> Unit, onCancel: () -> Unit) {
    DialogContent(
        title = "Remove Parent Card",
        confirmText = "Remove",
        onConfirm = onConfirm,
        onCancel = onCancel
    ) {
        Text("Are you sure you want to remove the parent card?")
    }
}

@Composable
fun UpgradeCardDialog(onConfirm: () -> Unit, onCancel: () -> Unit) {
    DialogContent(
        title = "Upgrade Card",
        confirmText = "Upgrade",
        onConfirm = onConfirm,
        onCancel = onCancel
    ) {
        Text("Are you sure you want to upgrade the card?")
    }
}

@Composable
fun DialogContent(
    title: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = Cream
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            content()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onCancel) {
                    Text("Cancel")
                }

                TextButton(onClick = onConfirm) {
                    Text(confirmText)
                }
            }
        }
    }
}


@Preview
@Composable
fun CardPrev(){
    CardScreen(rememberNavController())
}