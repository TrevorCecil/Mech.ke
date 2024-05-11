package com.example.Mech_ke.ui.theme.screens.user_showroom

import android.content.Intent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.Mech_ke.R
import com.example.Mech_ke.data.ShowroomViewModel
import com.example.Mech_ke.models.Showroom
import com.example.Mech_ke.navigation.WEBVIEW_URL
import com.example.Mech_ke.ui.theme.newBlue
import com.example.Mech_ke.ui.theme.newWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserShowScreen(navController: NavHostController){

    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF4BCCFF), Color(0xFF2C81F3))
    )
    Column(modifier = Modifier
        .background(gradient)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {



        var context = LocalContext.current
        var productRepository = ShowroomViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Showroom("","","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Showroom>() }

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
            Text(text = "SHOW ROOMS",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = newWhite
            )


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
fun UserViewItem(name:String, contact:String, county:String, town:String, id:String,
                 navController: NavHostController,
                 productRepository: ShowroomViewModel, productImage:String) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF4BCCFF), Color(0xFF2C81F3))
    )

    Column() {
        Card (modifier = Modifier
            .padding(top = 20.dp)
            .height(350.dp)
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

                Row {
                    val mContext = LocalContext.current

                    Text(text = "CALL",
                        Modifier
                            .clickable {
                                val smsIntent = Intent(Intent.ACTION_SENDTO)
                                smsIntent.data = "smsto:$contact".toUri()
                                mContext.startActivity(smsIntent)
                            }
                            .padding(bottom = 5.dp, start = 10.dp),
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = newBlue
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(text = "DIRECTION",
                        Modifier
                            .clickable {
                                navController.navigate(WEBVIEW_URL)
                            }
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
        Spacer(modifier = Modifier.height(15.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun UserShowPreviewScreen(){
    UserShowScreen(navController = rememberNavController())
}