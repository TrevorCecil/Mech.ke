package com.example.wazitoecommerce.ui.theme.screens.autoparts_shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.data.AutopartViewModel
import com.example.wazitoecommerce.data.TestViewModel
import com.example.wazitoecommerce.models.Autopart
import com.example.wazitoecommerce.models.Autoshop
import com.example.wazitoecommerce.navigation.ADD_AUTOREPAIRSHOP_URL
import com.example.wazitoecommerce.navigation.ADMIN_ADDAUTOPART_URL
import com.example.wazitoecommerce.ui.theme.newBlue
import com.example.wazitoecommerce.ui.theme.newWhite
import com.example.wazitoecommerce.ui.theme.screens.autorepairshop.ViewAutorepairScreen
import com.example.wazitoecommerce.ui.theme.screens.autorepairshop.ViewItem

// AutorepairScreen.kt
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ViewAutopart_Screen(navController: NavHostController) {
    Column(modifier = Modifier
        .background(newBlue)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {



        var context = LocalContext.current
        var productRepository = AutopartViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Autopart("","","","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Autopart>() }

        var products = productRepository.allProducts(emptyProductState, emptyProductsListState)



        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row{
                Image(painter = painterResource(id = R.drawable.img), contentDescription = "spanner",
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .size(50.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = "MECH.ke",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = newWhite,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "AUTO PARTS SHOP",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = newWhite
            )

            LazyColumn(){

                items(products){

                    ViewItem(

                        productImage = it.imageUrl,

                        name = it.name,

                        price = it.price,

                        condition = it.condition,

                        location = it.location,

                        description = it.description,

                        id = it.id,

                        navController = navController,

                        productRepository = productRepository

                    )

                }

            }
        }
    }
}

@Composable
fun ViewItem(name:String, price:String, condition:String, location:String,description:String, id:String,
             navController:NavHostController,
             productRepository: AutopartViewModel, productImage:String) {

    Card (modifier = Modifier
        .padding(top = 20.dp)
        .height(300.dp)
        .width(240.dp)){
        Column (modifier = Modifier
            .fillMaxSize()
            .background(newWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
            Image(
                painter = rememberAsyncImagePainter(productImage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 20.dp, end = 20.dp, top = 10.dp)
                    .size(150.dp)
            )
            Column (modifier = Modifier
                .padding(start = 25.dp)
                .fillMaxWidth(),
                horizontalAlignment = AbsoluteAlignment.Left
                ){
                Row {
                    Text(text = "Item:", fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black, modifier =
                        Modifier
                            .padding(bottom = 5.dp, start = 10.dp))

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(text = name, fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black, modifier =
                        Modifier
                            .padding(bottom = 5.dp))
                }

                Row {
                    Text(text = "Price:",fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black, modifier =
                        Modifier
                            .padding(bottom = 5.dp, start = 10.dp))

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(text = "Ksh.$price",fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black, modifier =
                        Modifier
                            .padding(bottom = 5.dp))
                }

                Row {
                    Text(text = "Location:",fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black, modifier =
                        Modifier
                            .padding(bottom = 10.dp, start = 10.dp))

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(text = location,fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black, modifier =
                        Modifier
                            .padding(bottom = 10.dp))
                }

            }
            Row {
                Text(text = "DELETE",
                    Modifier
                        .clickable { productRepository.deleteProduct(id) }
                        .padding(bottom = 5.dp, start = 10.dp),
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = newBlue
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = "UPDATE",
                    Modifier
                        .clickable { navController.navigate(ADMIN_ADDAUTOPART_URL) }
                        .padding(bottom = 5.dp, start = 10.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    color = newBlue
                )

            }

        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}


@Preview
@Composable
fun ViewAutopartScreenPreview(){
    ViewAutorepairScreen(navController = rememberNavController())
}