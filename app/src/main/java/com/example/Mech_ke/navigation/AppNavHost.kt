

package com.example.Mech_ke.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Mech_ke.ui.theme.screens.MyContent
import com.example.Mech_ke.ui.theme.screens.autoparts_shop.AddAutopartScreen
import com.example.Mech_ke.ui.theme.screens.autoparts_shop.AutopartScreen
import com.example.Mech_ke.ui.theme.screens.autoparts_shop.ViewAutopart_Screen
import com.example.Mech_ke.ui.theme.screens.autorepairshop.AddAutoRepairshopScreen
import com.example.Mech_ke.ui.theme.screens.autorepairshop.AutorepairshopScreen
import com.example.Mech_ke.ui.theme.screens.autorepairshop.ViewAutorepairScreen
import com.example.Mech_ke.ui.theme.screens.carwash.AddCarWashScreen
import com.example.Mech_ke.ui.theme.screens.carwash.CarWashScreen
import com.example.Mech_ke.ui.theme.screens.carwash.ViewCarWashScreen
import com.example.Mech_ke.ui.theme.screens.dashboard.DashBoardScreen
import com.example.Mech_ke.ui.theme.screens.dashboard.UserDashBoardScreen
import com.example.Mech_ke.ui.theme.screens.gas_station.AddGasScreen
import com.example.Mech_ke.ui.theme.screens.gas_station.GasScreen
import com.example.Mech_ke.ui.theme.screens.gas_station.ViewGasScreen
import com.example.Mech_ke.ui.theme.screens.login.AdminLoginScreen
import com.example.Mech_ke.ui.theme.screens.login.LoginScreen
import com.example.Mech_ke.ui.theme.screens.mechanics.AddMechanicScreen
import com.example.Mech_ke.ui.theme.screens.mechanics.MechanicsScreen
import com.example.Mech_ke.ui.theme.screens.mechanics.ViewMechanicScreen
import com.example.Mech_ke.ui.theme.screens.showroom.AddShowroomScreen
import com.example.Mech_ke.ui.theme.screens.showroom.ShowroomScreen
import com.example.Mech_ke.ui.theme.screens.showroom.ViewShowroomScreen
import com.example.Mech_ke.ui.theme.screens.signup.AdminSignupScreen
import com.example.Mech_ke.ui.theme.screens.signup.SignupScreen
import com.example.Mech_ke.ui.theme.screens.splash.SplashScreen
import com.example.Mech_ke.ui.theme.screens.towtruck.AddTruckScreen
import com.example.Mech_ke.ui.theme.screens.towtruck.TruckScreen
import com.example.Mech_ke.ui.theme.screens.towtruck.ViewTruckScreen
import com.example.Mech_ke.ui.theme.screens.tyre.AddTyreScreen
import com.example.Mech_ke.ui.theme.screens.tyre.TyreScreen
import com.example.Mech_ke.ui.theme.screens.tyre.ViewTyreScreen
import com.example.Mech_ke.ui.theme.screens.user_autoparts.UserAutopartScreen
import com.example.Mech_ke.ui.theme.screens.user_showroom.UserShowScreen
import com.example.Mech_ke.ui.theme.screens.user_tow.UserTowScreen
import com.example.Mech_ke.ui.theme.screens.user_tyre.UserTyreScreen
import com.example.Mech_ke.ui.theme.screens.userautoshop.UserAutoshopScreen
import com.example.Mech_ke.ui.theme.screens.usercarwash.UserCarWashScreen
import com.example.Mech_ke.ui.theme.screens.usergas_station.UserGasScreen
import com.example.Mech_ke.ui.theme.screens.usermechanic.UserMechanicScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL,

) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(LOGIN_URL) {
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL) {
            SignupScreen(navController = navController)
        }
        composable(SPLASH_URL) {
            SplashScreen(navController = navController)
        }
        composable(DASH_URL) {
            DashBoardScreen(navController = navController)
        }
        composable(AUTOREPAIR_URL) {
            AutorepairshopScreen(navController = navController)
        }
        composable(ADD_AUTOREPAIRSHOP_URL) {
            AddAutoRepairshopScreen(navController = navController)
        }
        composable(ADD_MECHANIC_URL) {
            AddMechanicScreen(navController = navController)
        }
        composable(MECHANICS_URL) {
            MechanicsScreen(navController = navController)
        }
        composable(VIEWAUTOSHOP_URL) {
            ViewAutorepairScreen(navController=navController)
        }
        composable(ADMINLOGIN_URL) {
            AdminLoginScreen(navController=navController)
        }
        composable(ADMINSIGNUP_URL) {
            AdminSignupScreen(navController=navController)
        }
        composable(VIEW_MECHANIC_URL) {
            ViewMechanicScreen(navController=navController)
        }
        composable(USER_DASH_URL) {
            UserDashBoardScreen(navController=navController)
        }
        composable(USERAUTOSHOP_URL) {
            UserAutoshopScreen(navController=navController)
        }
        composable(USERMECHANIC_URL) {
            UserMechanicScreen(navController=navController)
        }
        composable(ADMINCARWSH_URL) {
            CarWashScreen(navController=navController)
        }
        composable(ADMINADDCARWSH_URL) {
            AddCarWashScreen(navController=navController)
        }
        composable(ADMINVIEWCARWSH_URL) {
            ViewCarWashScreen(navController=navController)
        }
        composable(ADMINGAS_URL) {
            GasScreen(navController=navController)
        }
        composable(ADMINVIEWGAS_URL) {
            ViewGasScreen(navController=navController)
        }
        composable(ADMINADDGAS_URL) {
            AddGasScreen(navController=navController)
        }
        composable(ADMINTOW_URL) {
            TruckScreen(navController=navController)
        }
        composable(ADMINADDTOW_URL) {
            AddTruckScreen(navController=navController)
        }
        composable(ADMINVIEWTOW_URL) {
            ViewTruckScreen(navController=navController)
        }
        composable(ADMINROOM_URL) {
            ShowroomScreen(navController=navController)
        }
        composable(ADMINADDROOM_URL) {
            AddShowroomScreen(navController=navController)
        }
        composable(ADMINVIEWROOM_URL) {
            ViewShowroomScreen(navController=navController)
        }
        composable(ADMINTYRE_URL) {
            TyreScreen(navController=navController)
        }
        composable(ADMINADDTYRE_URL) {
            AddTyreScreen(navController=navController)
        }
        composable(ADMINVIEWTYRE_URL) {
            ViewTyreScreen(navController=navController)
        }
        composable(USERCARWSH_URL) {
            UserCarWashScreen(navController=navController)
        }
        composable(USERGAS_URL) {
            UserGasScreen(navController=navController)
        }
        composable(USERTOW_URL) {
            UserTowScreen(navController=navController)
        }
        composable(USERSHOW_URL) {
            UserShowScreen(navController=navController)
        }
        composable(USERTYRE_URL) {
            UserTyreScreen(navController=navController)
        }
        composable(ADMINAUTOPART_URL) {
            AutopartScreen(navController=navController)
        }
        composable(ADMIN_ADDAUTOPART_URL) {
            AddAutopartScreen(navController=navController)
        }
        composable(ADMIN_VIEWAUTOPART_URL) {
            ViewAutopart_Screen(navController=navController)
        }
        composable(USERAUTOPART_URL) {
            UserAutopartScreen(navController=navController)
        }
        composable(WEBVIEW_URL) {
            MyContent(navController=navController)
        }
    }
}