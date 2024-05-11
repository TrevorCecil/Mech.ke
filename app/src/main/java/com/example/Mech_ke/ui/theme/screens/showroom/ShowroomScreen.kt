package com.example.Mech_ke.ui.theme.screens.showroom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.Mech_ke.navigation.ADMINADDROOM_URL
import com.example.Mech_ke.navigation.ADMINVIEWROOM_URL
import com.example.Mech_ke.ui.theme.newBlue
import com.example.Mech_ke.ui.theme.newWhite

@Composable
fun ShowroomScreen(navController: NavHostController) {
    Column (modifier = Modifier
        .background(newBlue)
        .fillMaxSize()
    ){
        Box (modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center){

            Image(painter = painterResource(id = R.drawable.room), contentDescription = "amazon",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(70.dp),
                contentScale = ContentScale.FillBounds)

        }
        Text(text = "SHOWROOM", fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = newWhite,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ){


            Card (modifier = Modifier
                .clickable { navController.navigate(ADMINADDROOM_URL) }
                .width(210.dp)
                .height(250.dp)){
                Column (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(newWhite)
                        .fillMaxSize()
                ){
                    //Lottie Animation
                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                        R.raw.person))
                    val progress by animateLottieCompositionAsState(composition)
                    LottieAnimation(composition, progress,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(100.dp)
                    )
                    //End of lottie
                    Text(text = "Add Showroom",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp)
                    Image(imageVector = Icons.Default.Add, contentDescription = "add",
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(30.dp)
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Card (modifier = Modifier
                .clickable { navController.navigate(ADMINVIEWROOM_URL) }
                .width(210.dp)
                .height(250.dp)){

                Column (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(newWhite)
                        .fillMaxSize()
                ){
                    //Lottie Animation
                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                        R.raw.person))
                    val progress by animateLottieCompositionAsState(composition)
                    LottieAnimation(composition, progress,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(100.dp)
                    )
                    //End of lottie
                    Text(text = "View Showrooms",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp)
                    Image(painter = painterResource(id = R.drawable.img_4), contentDescription = "Binoculars",
                        modifier = Modifier.size(30.dp)
                    )

                }


            }
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  ShowroomScreenPreview() {
    ShowroomScreen(rememberNavController())
}