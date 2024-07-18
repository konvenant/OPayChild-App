package com.example.opaychild.presentations.ui.screens.actions.savings

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PhoneAndroid
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

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GoalsScreen(
    navController: NavController
) {
    val dummyGoals = mutableListOf(
        Goal(
            title = "Dream Vacation",
            description = "Save up for a trip to Hawaii!",
            amount = 5000,
            saved = 1000,
            image = R.drawable.bicycle // Replace with your image resource ID
        ),
        Goal(
            title = "New Phone",
            description = "Upgrade to the latest smartphone",
            amount = 1200,
            saved = 500,
            image = R.drawable.boy4 // Replace with your image resource ID
        ),
        Goal(
            title = "Emergency Fund",
            description = "Build a safety net for unexpected expenses",
            amount = 2000,
            saved = 750,
            image = R.drawable.icfriend_selected // Replace with your image resource ID
        ),
        Goal(
            title = "Fitness Challenge",
            description = "Run a 5K race in 3 months",
            amount = 0, // No set amount for a non-monetary goal
            saved = 0,
            image = R.drawable.girl2 // Replace with your image resource ID
        )
    )
    var goals by remember { mutableStateOf(dummyGoals) }

    Goal(
        navController,
        goals = goals,
        onAddGoal = { newGoal ->
            goals = (goals + newGoal).toMutableList()
        },
        onAddMoneyToGoal = { goal, amount ->
            goals = goals.map {
                if (it == goal) {
                    it.copy(saved = it.saved + amount)
                } else {
                    it
                }
            }.toMutableList()
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Goal(
    navController: NavController,
    goals: MutableList<Goal>,
    onAddGoal: (Goal) -> Unit,
    onAddMoneyToGoal: (Goal, Int) -> Unit
) {

    var showAddGoalModal by remember { mutableStateOf(false) }
    var selectedGoal by remember { mutableStateOf<Goal?>(null) }
    var showAddMoneyModal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    "Goals",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    fontFamily = font1,
                    color = Green
                ) },
                actions = {
                    IconButton(
                        onClick = { showAddGoalModal = true },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Pink,
                            contentColor = White
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Goal")
                    }
                },
                navigationIcon = {
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
                }
            )
        },
        content = {
           if (goals.isEmpty()){
               Image(
                   painter = painterResource(id = R.drawable.img3),
                   contentDescription = null,
                   modifier = Modifier
                       .fillMaxSize(),

               )
           } else{
               LazyColumn (
                   contentPadding = PaddingValues(bottom = 56.dp, top = 52.dp)
               ) {
                   items(goals) { goal ->
                       GoalItem(goal = goal) {
                           selectedGoal = goal
                           showAddMoneyModal = true
                       }
                   }
               }
           }
        }
    )

    if (showAddGoalModal) {
        AddGoalModal(
            onDismiss = { showAddGoalModal = false },
            onAdd = onAddGoal
        )
    }

    selectedGoal?.let { goal ->
        if (showAddMoneyModal) {
            AddMoneyToGoalModal(
                goal = goal,
                onDismiss = { showAddMoneyModal = false },
                onAddMoney = { amount ->
                    onAddMoneyToGoal(goal, amount)
                },
                onDelete = {
                    goals.remove(goal)
                    showAddMoneyModal = false
                }
            )
        }
    }
}



@Composable
fun AddGoalModal(onDismiss: () -> Unit, onAdd: (Goal) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var image by remember { mutableIntStateOf(R.drawable.bicycle) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Add New Goal",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val goal = Goal(title, description, amount.toInt(), 0, image)
                        onAdd(goal)
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Add Goal")
                }
            }
        }
    }
}


@Composable
fun AddMoneyToGoalModal(
    goal: Goal,
    onDismiss: () -> Unit,
    onAddMoney: (Int) -> Unit,
    onDelete: (Goal) -> Unit
) {
    var amount by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                ){
                    Image(
                        painter = painterResource(id = goal.image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = {
                                  onDelete(goal)
                        },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = White)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Red,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Add Money to ${goal.title}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onAddMoney(amount.toInt())
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Add Money")
                }
            }
        }
    }
}





@Composable
fun GoalItem(goal: Goal, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp)
            .clickable { onClick() }
    ) {

        Image(
            painter = painterResource(id = goal.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(
                text = goal.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = goal.description,
                fontSize = 14.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Amount: \$${goal.amount}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )


            LinearProgressIndicator(
                color = Green,
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth(),
                trackColor = Color.Gray,
                strokeCap = StrokeCap.Round,
                progress = goal.saved / goal.amount.toFloat()
            )
            Text(
                text = "Saved: \$${goal.saved} / \$${goal.amount}",
                fontSize = 14.sp,
                color = Color.White
            )


        }
        if (goal.saved >= goal.amount) {
            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            ) {
                Text(text = "Get")
            }
        }
    }
}




data class Goal(
    val title: String,
    val description: String,
    val amount: Int,
    val saved: Int,
    val image: Int // Image resource id
)


@Preview
@Composable
fun GoalScreenPrev(){
    GoalsScreen(rememberNavController())
}
