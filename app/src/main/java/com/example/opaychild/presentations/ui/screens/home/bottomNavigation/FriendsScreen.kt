package com.example.opaychild.presentations.ui.screens.home.bottomNavigation

import android.annotation.SuppressLint
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opaychild.R
import com.example.opaychild.data.models.Friend
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Red
import com.example.opaychild.ui.theme.White
import com.example.opaychild.ui.theme.Yellow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FriendsScreen(
    navController: NavController
){


    val friends = listOf(
        Friend("Bob", 123456, R.drawable.boy1, true, 5),
        Friend("Alice", 654321, R.drawable.girl1, false, 3),
        Friend("Charlie", 789123, R.drawable.boy4, true, 4),
        Friend("Kudi", 123456, R.drawable.girl2, true, 5),
        Friend("Nathalie", 654321, R.drawable.girl3, false, 3),
        Friend("Frank", 789123, R.drawable.boy3, true, 4)
    )
    FriendsList(friends = friends, navController )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsList(
    friends: List<Friend>,
    navController: NavController
) {
    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 175.dp)
    } else StaggeredGridCells.Fixed(2)

    var showFriendModal by remember {
        mutableStateOf(false)
    }

    val fr = Friend("Bob", 123456, R.drawable.boy1, true, 5)
    var selectedFriendForModal by remember {
        mutableStateOf<Friend>(fr)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ){


           Text(
               text = "Friends",
               modifier = Modifier.padding(16.dp),
               color = Pink,
               fontWeight = FontWeight.Bold,
               fontFamily = font1,
               fontSize = 32.sp
           )

         BadgedBox(
             badge = {
             Text(
                 text = "5",
                 color = White,
                 modifier = Modifier
                     .background(
                         Pink,
                         CircleShape
                     )
                     .padding(3.dp)
                     .clip(CircleShape),

                 fontSize = 10.sp
             )
         },
             modifier = Modifier.padding(end = 16.dp)
         ) {
             Icon(
                 imageVector = Icons.Default.PeopleOutline,
                 contentDescription = null,
                 tint = Blue,
                 modifier = Modifier.clickable {

                 }
             )
         }
       }
        Button(
            onClick = { /* Handle add friend action */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            )
        ) {
            Icon(
                imageVector = Icons.Default.PersonAdd,
                contentDescription = "Add Friend"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add Friend")
        }

        LazyVerticalStaggeredGrid(
            columns = cellConfiguration,
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 72.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            modifier = Modifier.padding(bottom=16.dp)
        ) {
            items(friends) { friend ->
                FriendItem(friend){
                  showFriendModal = true
                    selectedFriendForModal = it
                }
            }
        }

        if (showFriendModal){
            selectedFriendForModal.let { FriendModal(onDismiss = { showFriendModal = false }, friend = it) }
        }
    }
}

@Composable
fun FriendItem(
    friend: Friend,
    onFriendClicked: (Friend) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, Pink, RoundedCornerShape(10.dp))
            .clickable { onFriendClicked(friend) }
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = friend.image),
            contentDescription = friend.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))

        )




        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
                .clip(RoundedCornerShape(11.dp))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text(
                text = friend.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
        if (friend.verified) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = "Verified",
                tint = Green,
                modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun FriendModal(
    onDismiss: () -> Unit,
    friend: Friend
){
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties()
    ) {
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
               .fillMaxWidth()
               .background(White)
               .clip(RoundedCornerShape(10.dp))
               .padding(2.dp)
       ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(16.dp)
        ){
          ElevatedButton(
              onClick = { onDismiss() },
              colors = ButtonDefaults.elevatedButtonColors(
                  containerColor = White,
                  contentColor = Pink
              ),
              elevation = ButtonDefaults.elevatedButtonElevation(6.dp)
          ) {
              Icon(
                  imageVector = Icons.Outlined.ArrowBackIos,
                  contentDescription = null
              )
          }

            ElevatedButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = White,
                    contentColor = Pink
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Red
                )
            }
        }
           
           Spacer(modifier = Modifier.height(16.dp))

           Box(
               modifier = Modifier
                   .clip(RoundedCornerShape(10.dp))
                   .height(400.dp)
           ) {
               Image(
                   painter = painterResource(id = friend.image),
                   contentDescription = friend.name,
                   contentScale = ContentScale.Crop,
                   modifier = Modifier
                       .fillMaxSize()
                       .clip(RoundedCornerShape(10.dp))

               )

               Box(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Color.Black.copy(alpha = 0.2f))
                       .clip(RoundedCornerShape(11.dp))
               )
               Box(
                   modifier = Modifier
                       .align(Alignment.BottomCenter)
                       .fillMaxWidth()
               ) {
                   Column (
                       verticalArrangement = Arrangement.SpaceBetween,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.fillMaxWidth()
                   ){

                   Column (
                       verticalArrangement = Arrangement.SpaceBetween,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.fillMaxWidth()
                   ) {
                       Text(
                           text = friend.name,
                           color = Color.White,
                           fontSize = 20.sp,
                           fontWeight = FontWeight.Bold,
                           modifier = Modifier
                               .padding(8.dp)
                       )

                       LazyRow {
                           items(friend.rating){
                               Icon(
                                   imageVector = Icons.Default.Star,
                                   contentDescription = null,
                                   tint = Yellow
                               )
                           }
                       }

                   }

                       Row (
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(8.dp),
                           verticalAlignment = Alignment.CenterVertically,
                           horizontalArrangement = Arrangement.SpaceEvenly
                       ) {
                           Column (
                               verticalArrangement = Arrangement.SpaceBetween,
                               horizontalAlignment = Alignment.CenterHorizontally,
                               modifier = Modifier
                                   .clip(RoundedCornerShape(10.dp))
                                   .background(Color.Transparent)
                           ) {
                               Icon(
                                   imageVector = Icons.Default.CreditCard,
                                   contentDescription = null,
                                   tint = White,
                               )

                               Text(text = "Send", color = White)
                           }

                           Column (
                               verticalArrangement = Arrangement.SpaceBetween,
                               horizontalAlignment = Alignment.CenterHorizontally,
                               modifier = Modifier
                                   .clip(RoundedCornerShape(10.dp))
                                   .background(Color.Transparent)
                           ) {
                               Icon(
                                   imageVector = Icons.Default.MonetizationOn,
                                   contentDescription = null,
                                   tint = White
                               )

                               Text(text = "Request", color = White)
                           }

                           Column (
                               verticalArrangement = Arrangement.SpaceBetween,
                               horizontalAlignment = Alignment.CenterHorizontally,
                               modifier = Modifier
                                   .clip(RoundedCornerShape(10.dp))
                                   .background(Color.Transparent)
                           ) {
                               Icon(
                                   imageVector = Icons.Default.Message,
                                   contentDescription = null,
                                   tint = White
                               )

                               Text(text = "Message", color = White)
                           }
                       }
                   }
               }
               if (friend.verified) {
                   Icon(
                       imageVector = Icons.Outlined.VerifiedUser,
                       contentDescription = "Verified",
                       tint = Green,
                       modifier = Modifier
                           .size(64.dp)
                           .align(Alignment.TopEnd)
                           .padding(8.dp)
                   )
               }
           }


           Text(
               text = "History",
               fontFamily = font1,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(start = 8.dp, top = 16.dp),
               color = Blue
           )

           LazyRow (modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp)) {
               items(createDummyHistoryListForFriend(friend.image)){
                   HistoryItemRow(
                       amount = it.amount,
                       type = it.type,
                       image = it.image,
                       icon = it.icon
                   ){

                   }
               }
           }
       }
    }
}

@Preview
@Composable
fun FriendsPrev(){
    FriendsScreen(rememberNavController())
}