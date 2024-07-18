package com.example.opaychild.presentations.ui.screens.onboarding.welcome


import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.opaychild.R
import com.example.opaychild.data.models.UserPrefs
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    var showLogo by remember { mutableStateOf(true) }
    var showGif by remember { mutableStateOf(false) }
    var showSplash by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val userPref = remember {
        userViewModel.userPref
    }

    LaunchedEffect(Unit) {
        userViewModel.getUserPref()
        delay(1500)
        showLogo = false
        showGif = true
        delay(4500)
        handleNavigation(navController,userViewModel,userPref)
    }

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (showSplash) {
            AnimatedVisibility(visible = showLogo) {
                ZoomingImage(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    initialFloat = 150f,
                    targetFloat = 200f
                )
            }

            AnimatedVisibility(visible = showGif) {

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(data = R.drawable.splash).apply(block = {
                            size(Size.ORIGINAL)
                        }).build(), imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

 private fun handleNavigation(
     navController: NavController,
     viewModel: UserViewModel,
     userPref: MutableState<UserPrefs>
 ){
     if (userPref.value.isFirstTime){
         navController.navigate(NavHelper.WelcomeScreen.route) {
             popUpTo(NavHelper.SplashScreen.route) {
                 inclusive = true
             }
         }
     } else{
         if (userPref.value.isLoggedIn){
             navController.navigate(NavHelper.HomeScreen.route) {
                 popUpTo(NavHelper.SplashScreen.route) {
                     inclusive = true
                 }
             }
         } else{
             navController.navigate(NavHelper.LoginScreen.route) {
                 popUpTo(NavHelper.SplashScreen.route) {
                     inclusive = true
                 }
             }

         }
     }
 }


@Composable
fun ZoomingImage(
    painter: Painter,
    contentDescription: String?,
    initialFloat : Float,
    targetFloat: Float
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val size by infiniteTransition.animateFloat(
        initialValue = initialFloat,
        targetValue = targetFloat,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier.size(size.dp)
    )
}

@Preview
@Composable
fun SplashPrev(){

}