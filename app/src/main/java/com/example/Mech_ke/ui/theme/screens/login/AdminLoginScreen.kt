package com.example.Mech_ke.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.Mech_ke.R
import com.example.Mech_ke.data.AdminViewModel
import com.example.Mech_ke.navigation.ADMINSIGNUP_URL
import com.example.Mech_ke.navigation.LOGIN_URL
import com.example.Mech_ke.ui.theme.WazitoECommerceTheme
import com.example.Mech_ke.ui.theme.newBlue
import com.example.Mech_ke.ui.theme.newWhite

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AdminLoginScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .background(color = newBlue)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row (modifier = Modifier
            .padding(top = 30.dp)){
            Image(painter = painterResource(id = R.drawable.img), contentDescription = "spanner",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(50.dp))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "MECH.ke",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = newWhite,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        //Lottie Animation
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.person))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(composition, progress,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .size(150.dp)
        )
        //End of lottie
        Text(text = "Welcome Back!",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = newWhite,
            modifier = Modifier.padding(start = 5.dp))
        Text(text = "Please login to Mech.ke with your admin Mech.ke Account",
            color = newWhite
        )
        Spacer(modifier = Modifier.height(30.dp))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Enter email *",
                color = newWhite
            )
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email",
                tint = Color.Black
            )
            },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(

                focusedBorderColor = Color.Black,

                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Enter password *",
                color = newWhite
            )
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "password",
                tint = Color.Black)
            },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(

                focusedBorderColor = Color.Black,

                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(30.dp))
        val context = LocalContext.current
        val authViewModel = AdminViewModel(navController, context)
        Button(
            onClick = {  authViewModel.login(email, password,)},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(newWhite),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text(
                text = "Login",
                color = Color.Black)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Don't have an admin Account?Register.",
            textDecoration = TextDecoration.Underline,
            color = newWhite,
            fontSize = 12.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier =
            Modifier
                .clickable {
                    navController.navigate(ADMINSIGNUP_URL)
                }
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Login as User.",
            textDecoration = TextDecoration.Underline,
            fontSize = 12.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier =
            Modifier
                .clickable {
                    navController.navigate(LOGIN_URL)
                }
        )


    }
}

@Composable
@Preview(showBackground = true)
fun AdminLoginScreenPreview(){
    WazitoECommerceTheme {
        AdminLoginScreen(navController = rememberNavController())
    }
}