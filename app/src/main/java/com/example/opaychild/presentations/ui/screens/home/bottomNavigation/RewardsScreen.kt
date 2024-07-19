package com.example.opaychild.presentations.ui.screens.home.bottomNavigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opaychild.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.ui.utils.FontHolders.font3
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Green20
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Pink30
import com.example.opaychild.ui.theme.White
import com.example.opaychild.ui.theme.Yellow


@Composable
fun RewardScreen() {
    var rewardBalance by remember { mutableIntStateOf(1000) }
    var dailyRewardClaimed by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }

    val buttonColor by animateColorAsState(
        targetValue = if (dailyRewardClaimed) Green20 else Green,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Rewards", fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Green,
                fontFamily = font1
            )
            Box {
                IconButton(onClick = { showDropdown = !showDropdown }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icmenu2),
                        contentDescription = "More Options",
                        tint = Yellow
                    )
                }
                DropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = { showDropdown = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("Rules") },
                        onClick = { /* Handle Rules Click */ },
                        colors = MenuDefaults.itemColors(
                          textColor = Green
                        )
                    )
                    DropdownMenuItem(
                        text = { Text("History") },
                        onClick = { /* Handle History Click */ },
                        colors = MenuDefaults.itemColors(
                            textColor = Green
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Reward Balance: $rewardBalance points",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = font5,
            color = Pink
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = !dailyRewardClaimed) {
                    if (!dailyRewardClaimed) {
                        rewardBalance += 100
                        dailyRewardClaimed = true
                    }
                },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = buttonColor
            )
        ) {
            Text(
                text = if (dailyRewardClaimed) "Reward Claimed" else "Claim Daily Reward",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Daily Bonuses",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Green,
            fontFamily = font1
        )

        Spacer(modifier = Modifier.height(8.dp))

        val dailyBonuses = listOf(
            "Complete 5 transactions - 50 points",
            "Log in 7 days in a row - 200 points",
            "Refer a friend - 100 points"
        )

        dailyBonuses.forEach { bonus ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Pink30
                )
            ) {
               Row (
                   modifier = Modifier.fillMaxWidth(),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Text(
                       text = bonus,
                       modifier = Modifier.padding(12.dp),
                       fontSize = 13.sp,
                       fontFamily = font3,
                       color = White,
                       fontWeight = FontWeight.SemiBold,
                       overflow = TextOverflow.Ellipsis
                   )

                   ElevatedButton(
                       onClick = { /*TODO*/ },
                       colors = ButtonDefaults.elevatedButtonColors(
                         contentColor = Yellow,
                           containerColor = White
                       ),
                       modifier = Modifier.padding(8.dp)
                       ) {
                      Text(text = "Go")
                   }
               }
            }
        }
    }
}


@Preview
@Composable
fun RewardPrev(){
    RewardScreen()
}