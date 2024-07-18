package com.example.opaychild.presentations.ui.screens.onboarding.auth

import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
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
import com.example.opaychild.presentations.ui.utils.FontHolders.font4
import com.example.opaychild.presentations.ui.utils.FontHolders.font5
import com.example.opaychild.presentations.viewmodels.UserViewModel
import com.example.opaychild.ui.theme.Blue
import com.example.opaychild.ui.theme.Green
import com.example.opaychild.ui.theme.Pink
import com.example.opaychild.ui.theme.Red
import com.example.opaychild.ui.theme.White
import com.example.opaychild.ui.theme.Yellow
import java.util.*
import java.text.SimpleDateFormat

@Composable
fun RegisterScreen(
    navController:NavController,
    userViewModel: UserViewModel
) {
    var step by remember { mutableIntStateOf(0) }
    var name by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var localGovernment by remember { mutableStateOf("") }
    var homeAddress by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var favoriteColor by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var age by remember { mutableIntStateOf(0) }
    val createdAt = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }
    val modifier  = Modifier
        .background(Yellow, RoundedCornerShape(16.dp))
        .padding(12.dp)
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
            2 -> TextFieldWithContinue("Enter your state", state) { state = it; step++ }
            3 -> TextFieldWithContinue("Enter your local government", localGovernment) { localGovernment = it; step++ }
            4 -> TextFieldWithContinue("Enter your home address", homeAddress) { homeAddress = it; step++ }
            5 -> TextFieldWithContinue("Enter your display name", displayName) { displayName = it; step++ }
            6 -> TextFieldWithContinue("Enter your phone number", phoneNumber, KeyboardOptions(keyboardType = KeyboardType.Phone)) { phoneNumber = it; step++ }
            7 -> TextFieldWithContinue("Enter your favorite color", favoriteColor) { favoriteColor = it; step++ }
            8 -> ImagePickerField(imageUri, imagePickerLauncher) { imageUri = it; step++ }
            9 -> {
               LazyColumn(modifier = Modifier.fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                   item {
                       // Final screen showing summary and finish button
                       Row(
                           horizontalArrangement = Arrangement.SpaceEvenly,
                           verticalAlignment = Alignment.CenterVertically,
                           modifier = Modifier.fillMaxWidth()
                       ) {
                           Text(text = "Summary", style = MaterialTheme.typography.headlineSmall)
                           Text(
                               text = "Change",
                               style = MaterialTheme.typography.bodyLarge,
                               modifier = Modifier.clickable {
                                   step = 0
                               },
                               color = Red,
                               fontFamily = font5
                           )
                       }
                       Spacer(modifier = Modifier.height(16.dp))

                       Image(
                           painter = rememberAsyncImagePainter(imageUri),
                           contentDescription = null,
                           modifier = Modifier
                               .size(150.dp)
                               .clip(CircleShape),
                           contentScale = ContentScale.Crop
                       )
                       Spacer(modifier = Modifier.height(16.dp))
                       Text(text = "Name: $name",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Date of Birth: $dob (Age: $age)",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "State: $state",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Local Government: $localGovernment",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Home Address: $homeAddress",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Display Name: $displayName",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Phone Number: $phoneNumber",modifier, color = White)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text(text = "Favorite Color: $favoriteColor",modifier, color = White)
                       Spacer(modifier = Modifier.height(32.dp))
                       Button(
                           onClick = {
                               navController.navigate(NavHelper.VerifyScreen.route) {
                                   popUpTo(NavHelper.RegisterScreen.route) {
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

@Composable
fun TextFieldWithContinue(
    label: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onContinueClicked: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                            text = it
            },
            label = { Text(label) },
            keyboardOptions = keyboardOptions,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Green
            )
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = { onContinueClicked(text) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Pink
            ),
            enabled = text.isNotEmpty()
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun DatePickerField(label: String, value: String, onValueChange: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            onValueChange(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { datePickerDialog.show() }) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun ImagePickerField(
    imageUri: Uri?,
    imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>,
    onImagePicked: (Uri?) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (imageUri == null) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text(text = "Pick Image")
            }
            Spacer(modifier = Modifier.width(16.dp))
            TextButton(onClick = { onImagePicked(null) }) {
                Text(text = "Skip")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onImagePicked(imageUri) }) {
            Text(text = "Continue")
        }
    }
}


@Preview
@Composable
fun RegisterPrev(){
}