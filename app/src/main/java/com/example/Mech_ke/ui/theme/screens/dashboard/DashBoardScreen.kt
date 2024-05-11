package com.example.Mech_ke.ui.theme.screens.dashboard

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
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
import com.example.Mech_ke.navigation.ADMINAUTOPART_URL
import com.example.Mech_ke.navigation.ADMINCARWSH_URL
import com.example.Mech_ke.navigation.ADMINGAS_URL
import com.example.Mech_ke.navigation.ADMINROOM_URL
import com.example.Mech_ke.navigation.ADMINTOW_URL
import com.example.Mech_ke.navigation.ADMINTYRE_URL
import com.example.Mech_ke.navigation.AUTOREPAIR_URL
import com.example.Mech_ke.navigation.MECHANICS_URL
import com.example.Mech_ke.ui.theme.newBlue
import com.example.Mech_ke.ui.theme.newWhite

@Composable
fun DashBoardScreen(navController: NavHostController) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFFA9DFF8), Color(0xFF2C81F3))
    )
    Column (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .background(gradient)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Row {
            Image(painter = painterResource(id = R.drawable.img), contentDescription = "spanner",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(50.dp))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "MECH.ke",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = newWhite,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(top = 20.dp),
            )
        }
        Card (modifier = Modifier
            .height(200.dp)
            .padding(20.dp)){

            val gradient = Brush.horizontalGradient(
                listOf(Color(0xFFBE9FF5), Color(0xFF000000))
            )

            GradientCard(
                gradient = gradient
            )

        }
        Column (){
            //Row 1
            Row {
                Card (modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 5.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(AUTOREPAIR_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 15.dp)){
                        Box (modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){

                            Image(painter = painterResource(id = R.drawable.img_9), contentDescription = "amazon",
                                modifier = Modifier.size(50.dp))

                        }
                        Text(text = "Auto Repair shops", fontSize = 15.sp,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Card (modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 5.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(MECHANICS_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 15.dp)){
                        Box (modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){

                            Image(painter = painterResource(id = R.drawable.img_10), contentDescription = "amazon",
                                modifier = Modifier.size(50.dp))

                        }
                        Text(text = "Mechanics", fontSize = 15.sp,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

            }
            //End of row 1


            //Row 2
            Row {
                Card (modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 10.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(ADMINCARWSH_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 15.dp)){
                        Box (modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){

                            Image(painter = painterResource(id = R.drawable.img_5), contentDescription = "amazon",
                                modifier = Modifier.size(50.dp))

                        }
                        Text(text = "Car Wash", fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Card (modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 10.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(ADMINGAS_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 15.dp)){
                        Box (modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){

                            Image(painter = painterResource(id = R.drawable.img_12), contentDescription = "amazon",
                                modifier = Modifier.size(50.dp))

                        }
                        Text(text = "Gas Station", fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

            }
            //End of row 2

            //Row 3
            Row {
                Card (modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 10.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(ADMINTOW_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 15.dp)){
                        Box (modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){

                            Image(painter = painterResource(id = R.drawable.img_7), contentDescription = "amazon",
                                modifier = Modifier.size(50.dp))

                        }
                        Text(text = "Tow Truck", fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Card (modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 10.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(ADMINAUTOPART_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 15.dp)){
                        Box (modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){

                            Image(painter = painterResource(id = R.drawable.img_15), contentDescription = "amazon",
                                modifier = Modifier.size(50.dp))

                        }
                        Text(text = "Auto parts shop", fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

            }
            //End of row 3


            //Row 4
            Row {
                Card(modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 10.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(ADMINROOM_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(top = 15.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.room),
                                contentDescription = "amazon",
                                modifier = Modifier.size(50.dp)
                            )

                        }
                        Text(
                            text = "Showroom", fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Card(modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        ambientColor = newBlue,
                        elevation = 10.dp,
                        shape = RectangleShape,
                        spotColor = Color.Blue,
                        clip = false
                    )
                    .clickable { navController.navigate(ADMINTYRE_URL) }
                    .size(width = 150.dp, height = 100.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(top = 15.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.img_13),
                                contentDescription = "amazon",
                                modifier = Modifier.size(50.dp)
                            )

                        }
                        Text(
                            text = "Tyre & Service centre", fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                    }
                }

            }
            //End of row 4



        }

    }


}

@Composable

fun GradientCard(
    gradient: Brush) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Give your car a makeover.",
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp)
        )
        //Lottie Animation
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.service))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            composition, progress,
            modifier = Modifier
                .padding(start = 80.dp, bottom = 20.dp, top = 20.dp, end = 60.dp)
                .size(10000.dp)
        )
        //End of lottie

    }

}


@Preview(showBackground = true, showSystemUi =true)
@Composable
fun DashBoardScreenPreview() {
    DashBoardScreen(rememberNavController())
}