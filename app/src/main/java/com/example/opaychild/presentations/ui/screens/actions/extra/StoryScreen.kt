package com.example.opaychild.presentations.ui.screens.actions.extra

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opaychild.R
import com.example.opaychild.data.models.Status
import com.example.opaychild.data.models.Story
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

@Composable
fun StoryScreen(
 navController: NavController
) {
    val stories = listOf(
        Story(
            id = "1",
            title = "Adventure in the Forest",
            desc = "A thrilling adventure in the deep forest.",
            images = listOf(R.drawable.forest_story),
            date = getCurrentDate(),
            tags = listOf("Adventure", "Forest"),
            rating = 5,
            likes = 120,
            comments = listOf(),
            story = "In the heart of a quaint village nestled amidst rolling hills, lived a young musician named Elara. Music flowed through her veins, her nimble fingers dancing across the strings of her lute, weaving melodies that tugged at the heartstrings. Yet, amidst her passion, a yearning gnawed at her soul. Elara dreamt of a melody, ethereal and captivating, that drifted to her one starlit night. It vanished as quickly as it appeared, leaving behind a burning desire to find its source.\n" +
                    "\n" +
                    "One crisp autumn morning, armed with her lute and a knapsack filled with provisions, Elara set out on a journey. The melody, a beacon in her mind, guided her towards the whispering pines of an ancient forest. The sunlight filtered through the dense canopy, casting dappled shadows on the moss-covered ground. As she ventured deeper, the air grew cool and a symphony of rustling leaves filled the silence.\n" +
                    "\n" +
                    "Suddenly, the melody wafted through the air, clearer this time. Elara quickened her pace, her heart pounding with anticipation. The path narrowed, leading her to a hidden clearing bathed in an otherworldly glow. In the center stood a majestic willow tree, its branches cascading down like emerald curtains. And there, nestled amongst the roots, sat a small, silver bird with feathers that shimmered like moonlight.\n" +
                    "\n" +
                    "As Elara approached, the bird's eyes, pools of liquid gold, met hers. It opened its beak and poured forth the melody, a song of pure enchantment. Elara, entranced, closed her eyes and played along on her lute. The forest resonated with their music, the leaves swaying in rhythm. When the song ended, a feeling of peace settled over Elara. The bird fluttered its wings and disappeared into the sunbeams filtering through the leaves.\n" +
                    "\n" +
                    "Elara emerged from the forest a changed person. The melody now resided within her, a constant companion. She returned to her village, her music imbued with a newfound depth and power. The villagers listened in awe, their faces reflecting the emotions Elara poured into her music. Elara continued to travel, sharing the melody with others, inspiring hope and joy wherever she went. The source of the melody remained a mystery, but its magic resonated through her music, a testament to her courage and the power of following a dream.\n" +
                    "\n" +
                    "\n"
        ),
        Story(
            id = "2",
            title = "Beach Fun",
            desc = "A day at the sunny beach.",
            images = listOf(R.drawable.beach_story),
            date = getCurrentDate() + 86400000L, // next day
            tags = listOf("Beach", "Fun"),
            rating = 4,
            likes = 98,
            comments = listOf()
        )
        // Add more stories
    )


    Story(stories = stories, navController)

}


@Composable
fun Story(
    stories: List<Story>,
    navController: NavController
) {
    val (selectedStory, setSelectedStory) = remember { mutableStateOf<Story?>(null) }

    val currentStory = stories.firstOrNull { it.date == getCurrentDate() }
    val upcomingStories = stories.filter { it.date > getCurrentDate() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

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
                    text = "Story",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = font1
                )
            }
            currentStory?.let {
                Text(
                    text = "Today's Story",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Pink,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = font2
                )
                StoryItem(story = it, isCurrent = true, onClick = setSelectedStory)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = "Upcoming Stories",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(16.dp),
                fontFamily = font2
            )
            StoryList(stories = upcomingStories, onClick = setSelectedStory)
        }
    }

    selectedStory?.let {
        StoryModal(story = it, onDismiss = { setSelectedStory(null) })
    }
}

@Composable
fun StoryList(stories: List<Story>, onClick: (Story) -> Unit) {
    Column (
    ) {
        val isCurrent = stories[0].date == getCurrentDate()
        StoryItem(story = stories[1], isCurrent = isCurrent, onClick = onClick)
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun getCurrentDate(): String {
    // Function to get the current date
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd") // Adjust format as needed
    val currentDate = Date(System.currentTimeMillis())
    return dateFormatter.format(currentDate)
}



@Composable
fun StoryItem(story: Story, isCurrent: Boolean, onClick: (Story) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(story) },
        colors = CardDefaults.cardColors(
          containerColor = if (isCurrent) White else Green20
        ) ,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = story.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCurrent) Green else Blue,
                fontFamily = font1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = story.desc,
                fontSize = 18.sp,
                color = if (isCurrent) Green else White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontFamily = font5

            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = story.images.first()),
                contentDescription = story.title,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = if (isCurrent) Yellow else Color(0xFFFFC107)
                )
                Text(
                    text = story.rating.toString(),
                    fontSize = 14.sp,
                    color = if (isCurrent) Blue else Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Likes",
                    tint = if (isCurrent) Red else Color(0xFFF44336)
                )
                Text(
                    text = story.likes.toString(),
                    fontSize = 14.sp,
                    color = if (isCurrent) Blue else Color.Black
                )
            }
        }
    }
}


@Composable
fun StoryModal(story: Story, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        LazyColumn {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = story.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Blue
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = story.images.first()),
                            contentDescription = story.title,
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = story.desc,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        story.story?.let {
                            Text(
                                text = it,
                                fontSize = 15.sp,
                                color = Blue
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color.Yellow
                            )
                            Text(
                                text = story.rating.toString(),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Likes",
                                tint = Color.Red
                            )
                            Text(
                                text = story.likes.toString(),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "Close")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun StoryScreenPrev(){
    StoryScreen(rememberNavController())
}