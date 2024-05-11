package com.example.wazitoecommerce.ui.theme.screens.autorepairshop

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.navigation.ADD_AUTOREPAIRSHOP_URL
import com.example.wazitoecommerce.navigation.AUTOREPAIR_URL
import com.example.wazitoecommerce.navigation.DASH_URL
import com.example.wazitoecommerce.navigation.VIEWAUTOSHOP_URL
import com.example.wazitoecommerce.ui.theme.newBlue
import com.example.wazitoecommerce.ui.theme.newWhite

@Composable
fun AutorepairshopScreen(navController: NavHostController) {
    Column (modifier = Modifier
        .background(newBlue)
        .fillMaxSize()
        ){
        Box (modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center){

            Image(painter = painterResource(id = R.drawable.img_9), contentDescription = "amazon",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(70.dp),
                contentScale = ContentScale.FillBounds)

        }
        Text(text = "AUTO REPAIR SHOP", fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = newWhite,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ){


            Card (modifier = Modifier
                .clickable { navController.navigate(ADD_AUTOREPAIRSHOP_URL) }
                .width(210.dp)
                .height(240.dp)){
                Column (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(newWhite)
                        .fillMaxSize()
                    ){
                    //Lottie Animation
                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.person))
                    val progress by animateLottieCompositionAsState(composition)
                    LottieAnimation(composition, progress,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(100.dp)
                    )
                    //End of lottie
                    Text(text = "Add Auto repair shop",
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

            Spacer(modifier = Modifier.height(30.dp))

            Card (modifier = Modifier
                .clickable { navController.navigate(VIEWAUTOSHOP_URL) }
                .width(210.dp)
                .height(240.dp)){

                Column (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(newWhite)
                        .fillMaxSize()
                ){
                    //Lottie Animation
                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.person))
                    val progress by animateLottieCompositionAsState(composition)
                    LottieAnimation(composition, progress,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(100.dp)
                    )
                    //End of lottie
                    Text(text = "View Auto repair shops",
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
fun  AutorepairshopScreenPreview() {
    AutorepairshopScreen(rememberNavController())
}