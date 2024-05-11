package com.example.Mech_ke.ui.theme.screens.userautoshop

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.Mech_ke.R
import com.example.Mech_ke.data.TestViewModel
import com.example.Mech_ke.models.Autoshop
import com.example.Mech_ke.navigation.WEBVIEW_URL
import com.example.Mech_ke.ui.theme.newBlue
import com.example.Mech_ke.ui.theme.newWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAutoshopScreen(navController: NavHostController){

    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF4BCCFF), Color(0xFF2C81F3))
    )
    Column(modifier = Modifier
        .background(gradient)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {



        var context = LocalContext.current
        var productRepository = TestViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Autoshop("","","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Autoshop>() }

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
            Text(text = "AUTO REPAIR SHOPS",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = newWhite)

            //search bar

            var searchQuery by remember {
                mutableStateOf("")
            }

            OutlinedTextField(

                value = searchQuery,

                onValueChange = { searchQuery = it },

                label = { Text("Search",
                    color = Color.Black
                    ) },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search",
                    tint = Color.Black
                )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(

                    focusedBorderColor = Color.Black,

                    unfocusedBorderColor = Color.Black,
                    containerColor = newWhite,
                    cursorColor = newBlue
                ),

                modifier = Modifier

                    .fillMaxWidth()

                    .padding(16.dp)

            )

            //end of search bar

            Spacer(modifier = Modifier.height(20.dp))


            LazyColumn(){
                items(products){
                    UserViewItem(
                        productImage = it.imageUrl,
                        name = it.name,
                        county = it.county,
                        town = it.town,
                        contact = it.contact,
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
fun UserViewItem(name:String,contact:String, county:String, town:String, id:String,
             navController:NavHostController,
             productRepository: TestViewModel, productImage:String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card (modifier = Modifier
            .padding(top = 20.dp)
            .height(355.dp)
            .width(240.dp)){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(newWhite),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val mContext = LocalContext.current

                    OutlinedButton(
                        onClick = {
                            val smsIntent = Intent(Intent.ACTION_SENDTO)
                            smsIntent.data = "smsto:$contact".toUri()
                            mContext.startActivity(smsIntent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp)
                            .height(60.dp)
                            .width(150.dp),
                        colors = ButtonDefaults
                            .outlinedButtonColors(Color.White)
                    ) {

                        Text(
                            text = "Call Now",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black
                        )

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))

        Button(onClick = { navController.navigate(WEBVIEW_URL) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(newWhite),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp, end = 100.dp)
            ) {

            Text(text = "Get Directions",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )

        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun UserAutoshopPreviewScreen(){
    UserAutoshopScreen(navController = rememberNavController())
}