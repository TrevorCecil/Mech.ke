package com.example.Mech_ke.ui.theme.screens.mechanics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.Mech_ke.R
import com.example.Mech_ke.data.MechanicViewModel
import com.example.Mech_ke.models.Mechanic
import com.example.Mech_ke.navigation.ADD_MECHANIC_URL
import com.example.Mech_ke.ui.theme.WazitoECommerceTheme
import com.example.Mech_ke.ui.theme.newBlue
import com.example.Mech_ke.ui.theme.newWhite
@Composable
fun ViewMechanicScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .background(newBlue)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {



        var context = LocalContext.current
        var productRepository = MechanicViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Mechanic("","","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Mechanic>() }

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
            Text(text = "Mechanics",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = newWhite
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(products){
                    ViewItem(
                        productImage = it.imageUrl,
                        name = it.name,
                        contact = it.contact,
                        county = it.county,
                        town = it.town,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository,

                        )
                }
            }
        }
    }
}


@Composable
fun ViewItem(name:String, contact:String, county:String, town:String, id:String,
             navController: NavHostController,
             productRepository: MechanicViewModel, productImage:String) {

    Column() {
        Card (modifier = Modifier
            .padding(top = 20.dp)
            .height(350.dp)
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

                Column (Modifier
                    .padding(start = 25.dp)
                    .fillMaxWidth(),
                    horizontalAlignment = AbsoluteAlignment.Left
                    ){
                    Row {
                        Text(
                            text = "Name:", fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp, start = 20.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = name, fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp)
                        )
                    }

                    Row {
                        Text(
                            text = "Contact:", fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp, start = 20.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = contact, fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp)
                        )
                    }

                    Row {
                        Text(
                            text = "County:", fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp, start = 20.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = county, fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp)
                        )
                    }

                    Row {
                        Text(
                            text = "Town:", fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp, start = 20.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = town, fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black, modifier =
                            Modifier
                                .padding(bottom = 10.dp)
                        )
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
                            .clickable { navController.navigate(ADD_MECHANIC_URL) }
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
}

@Composable
@Preview(showBackground = true)
fun ViewMechanicScreenPreview(){
    WazitoECommerceTheme {
        ViewMechanicScreen(navController = rememberNavController())
    }
}