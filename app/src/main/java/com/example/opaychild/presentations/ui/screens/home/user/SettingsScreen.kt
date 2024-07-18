package com.example.opaychild.presentations.ui.screens.home.user

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opaychild.R
import com.example.opaychild.data.models.Status
import com.example.opaychild.data.models.TransactionType
import com.example.opaychild.data.models.Transactions
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
fun SettingsScreen (
    navController: NavController
) {
    Settings(navController)
}


@Composable
fun Settings(
    navController: NavController
) {
    val settingsItems = listOf(
        SettingsItem(id = "1", title = "Login Settings", icon = R.drawable.ic_fingeprint, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "2", title = "Payment Settings", icon = R.drawable.img8, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "3", title = "Saving Settings", icon = R.drawable.img6, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "4", title = "Security Question", icon = R.drawable.verification, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "5", title = "SMS Alert Subscription", icon = R.drawable.ic_message_unselected, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "6", title = "Close Account", icon = R.drawable.icdelete2, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "7", title = "Security Center", icon = R.drawable.ic3, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "8", title = "About", icon = R.drawable.waving_child, rightIcon = R.drawable.ic_arrow_forward),
        SettingsItem(id = "9", title = "Logout", icon = R.drawable.assistant_272080)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
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

            Spacer(modifier = Modifier.width(32.dp))

            Text(
                text = "Settings",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Pink,
                fontFamily = font1,
                modifier = Modifier.padding(16.dp)
            )
        }


        LazyColumn {
            items(settingsItems) { item ->
                SettingsItem(item = item, onClick = { /* Handle item click */ })
            }
        }
    }
}


@Composable
fun SettingsItem(item: SettingsItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors( Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            item.rightIcon?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}



data class SettingsItem(
    val id: String,
    val title: String,
    val icon: Int, // Drawable resource ID for the left icon
    val rightIcon: Int? = null // Drawable resource ID for the right icon, optional
)

@Preview
@Composable
fun SettingsScreenPrev(){
    SettingsScreen(navController = rememberNavController())
}


