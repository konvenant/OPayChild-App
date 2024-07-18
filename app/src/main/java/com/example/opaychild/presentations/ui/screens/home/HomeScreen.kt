package com.example.opaychild.presentations.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opaychild.R
import com.example.opaychild.data.dataSource.UserPreferences
import com.example.opaychild.data.models.User
import com.example.opaychild.data.repository.UserRepositoryImpl
import com.example.opaychild.domain.GetUserUseCase
import com.example.opaychild.presentations.ui.graphs.BottomNavGraph
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.utils.FontHolders.font1
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    userViewModel: UserViewModel
){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember {
        derivedStateOf { currentBackStackEntry?.destination?.route?: NavHelper.HomeScreen.route }
    }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val user = remember {
        userViewModel.user
    }

    val bottomNavController = rememberNavController()

  Surface(
      modifier = Modifier.fillMaxSize()
  ) {
      ModalNavigationDrawer(
          drawerContent = {
              ModalDrawer(navController, scope,drawerState,user.value )
          },
          drawerState = drawerState
      ) {
         Scaffold(
             modifier = Modifier.fillMaxSize(),
             bottomBar = {
                 BottomNavBar(bottomNavController){
                   scope.launch {
                       drawerState.open()
                   }
                 }
             }
         ) {
             BottomNavGraph(userViewModel,navController,bottomNavController)
         }
      }
  }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    navController: NavController,
    onMenuClicked: () -> Unit
){

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = R.drawable.ic_home_selected,
            unSelectedIcon = R.drawable.ic_home_unselected,
            hasNew = false,
            action = {
                navController.navigate(NavHelper.DashboardScreen.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    selectedItemIndex = 0
                    restoreState = true
                }
            }
        ),
        BottomNavigationItem(
            title = "Rewards",
            selectedIcon = R.drawable.ic_reward_selected,
            unSelectedIcon = R.drawable.ic_reward_unselected,
            hasNew = false,
            action = {
                navController.navigate(NavHelper.RewardScreen.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    selectedItemIndex = 1
                    restoreState = true
                }
            }
        ),
        BottomNavigationItem(
            title = "Friends",
            selectedIcon = R.drawable.boy_waving_hand,
            unSelectedIcon = R.drawable.icfriend_unselected,
            hasNew = false,
            action = {
                navController.navigate(NavHelper.FriendsScreen.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    selectedItemIndex = 2
                    restoreState = true
                }
            }
        ),
        BottomNavigationItem(
            title = "Menu",
            selectedIcon = R.drawable.icmenu2,
            unSelectedIcon = R.drawable.icmenu,
            hasNew = false,
            action = {
             onMenuClicked()
            }
        ),
    )




    NavigationBar (
        modifier = Modifier
            .fillMaxWidth()
        ,
        containerColor =  White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == selectedItemIndex,
                onClick = {
                          bottomNavigationItem.action()
                },
                icon = {
                    BadgedBox(badge = {
                        if (bottomNavigationItem.badgeCount != null){
                            Badge{
                                Text(text = bottomNavigationItem.badgeCount.toString())
                            }
                        } else if(bottomNavigationItem.hasNew){
                            Badge()
                        }
                    }) {
                        Icon(
                            painter = if (index == selectedItemIndex) {
                                painterResource(bottomNavigationItem.selectedIcon )
                            } else painterResource(bottomNavigationItem.unSelectedIcon),
                            contentDescription = bottomNavigationItem.title
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = Pink,
                    selectedTextColor = Pink,
                    unselectedTextColor = Green,
                    unselectedIconColor = Green
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    user: User
){
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            unSelectedIcon = R.drawable.ic_home_selected,
            selectedIcon = R.drawable.ic_home_selected,
            hasNew = false,
            action = {

            }
        ),
        BottomNavigationItem(
            title = "Savings",
            unSelectedIcon = R.drawable.ic_savings_selected,
            selectedIcon = R.drawable.ic_savings_selected,
            hasNew = false,
            action = {
               navController.navigate(NavHelper.SavingsScreen.route)
            }
        ),
        BottomNavigationItem(
            title = "Story",
            unSelectedIcon = R.drawable.ic_story_selected,
            selectedIcon = R.drawable.ic_story_selected,
            hasNew = false,
            action = {
               navController.navigate(NavHelper.StoryScreen.route)
            }
        ),
        BottomNavigationItem(
            title = "Message",
            unSelectedIcon = R.drawable.ic_message_selected,
            selectedIcon = R.drawable.ic_message_selected,
            hasNew = false,
            action = {
                navController.navigate(NavHelper.MessageScreen.route)
            }
        ),
        BottomNavigationItem(
            title = "Card",
            unSelectedIcon = R.drawable.ic_card_unselected,
            selectedIcon = R.drawable.iccard2,
            hasNew = false,
            action = {
                navController.navigate(NavHelper.CardScreen.route)
            }
        ),
        BottomNavigationItem(
            title = "Profile",
            unSelectedIcon = R.drawable.ic_profile_unselected,
            selectedIcon = R.drawable.ic_profile_selected,
            hasNew = false,
            action = {
                navController.navigate(NavHelper.ProfileScreen.route)
            }
        ),
        BottomNavigationItem(
            title = "Settings",
            unSelectedIcon = R.drawable.ic_settings_unselected,
            selectedIcon = R.drawable.ic_settings_selected,
            hasNew = false,
            badgeCount = 2,
            action = {
                navController.navigate(NavHelper.SettingsScreen.route)
            }
        )
    )
    ModalDrawerSheet {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(Green)
                .padding(16.dp)
        ){
            Text(
                text = user.displayName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            AsyncImage(
                model = user.image,
                contentDescription = "user image",
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable { }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.title,
                        fontFamily = font1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = if (index == selectedItem) White else Blue
                    )
                },
                selected = index == selectedItem,
                onClick = {
                    selectedItem = index
                    scope.launch {
                        drawerState.close()
                    }
                    item.action()
                },
                icon = {
                    Icon(painter = if (index == selectedItem){
                       painterResource(item.selectedIcon)
                    } else  painterResource(item.unSelectedIcon)
                        , contentDescription = item.title,
                        tint = if (index == selectedItem) Pink else Green,
                        modifier  = Modifier
                            .padding(vertical = 8.dp)
                    )
                },
                badge = {
                    if (item.hasNew){
                        Badge(){
                            Text(text = item.badgeCount.toString())
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(16.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedIconColor = Pink,
                    selectedContainerColor = Green,
                    selectedTextColor = Blue
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val title:String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    val hasNew: Boolean,
    val badgeCount: Int? = null,
    val action: () -> Unit
)

@Preview
@Composable
fun HomePrev(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val userRepository  = UserRepositoryImpl(userPreferences)
    val getUserUseCase = GetUserUseCase(userRepository)
    val userViewModel = UserViewModel(getUserUseCase)

    HomeScreen(navController , userViewModel )
}