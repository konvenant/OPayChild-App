package com.example.opaychild.presentations.ui.screens.onboarding.setup

import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.opaychild.R
import com.example.opaychild.presentations.ui.graphs.NavHelper
import com.example.opaychild.presentations.ui.screens.onboarding.auth.ConnectToParentScreen
import com.example.opaychild.presentations.ui.screens.onboarding.auth.DatePickerField
import com.example.opaychild.presentations.ui.screens.onboarding.auth.ImagePickerField
import com.example.opaychild.presentations.ui.screens.onboarding.auth.TextFieldWithContinue
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.White
import com.example.opaychild.ui.theme.Yellow
import java.util.*
import java.text.SimpleDateFormat

@Composable
fun RegisterUserDetailsScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    var step by remember { mutableIntStateOf(0) }
    var name by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var favoriteColor by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var age by remember { mutableIntStateOf(0) }
    val createdAt = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }

    val modifier  = Modifier.background(Yellow, RoundedCornerShape(16.dp)).padding(12.dp)
    fun calculateAge(birthDate: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dob = sdf.parse(birthDate)
        val today = Date()
        val diff = today.time - dob.time
        val age = diff / (1000L * 60 * 60 * 24 * 365)
        return age.toInt()
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Register Your Details")

        Image(
            painter = painterResource(id = R.drawable.reg),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        when (step) {
            0 -> TextFieldWithContinue("Enter your name", name) { name = it; step++ }
            1 -> DatePickerField("Select your date of birth", dob) { dob = it; age = calculateAge(it); step++ }
            2 -> TextFieldWithContinue("Enter your display name", displayName) { displayName = it; step++ }
            3 -> TextFieldWithContinue("Enter your favorite color", favoriteColor) { favoriteColor = it; step++ }
            4 -> ImagePickerField(imageUri, imagePickerLauncher) { imageUri = it; step++ }
            5 -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        // Final screen showing summary and finish button
                        Text(text = "Summary", style = MaterialTheme.typography.headlineSmall,)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Name: $name",modifier, color = White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Date of Birth: $dob (Age: $age)",modifier, color = White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Display Name: $displayName",modifier, color = White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Favorite Color: $favoriteColor",modifier, color = White)
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = {
                                navController.navigate(NavHelper.HomeScreen.route) {
                                    popUpTo(NavHelper.RegisterUserDetailsScreen.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green
                            ),
                            modifier = Modifier.fillMaxWidth(0.7f)
                        ) {
                            Text(text = "Finish",modifier  = Modifier.padding(6.dp))
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun RegisterUserDetailsPrev(){

}