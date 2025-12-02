package com.example.kasihreview.View

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.NavObjects.MovieDetails
import com.example.kasihreview.NavObjects.UlasanPage
import com.example.kasihreview.NavObjects.WatchListPage
import com.example.kasihreview.NavObjects.daftarPage
import com.example.kasihreview.NavObjects.loginPage
import com.example.kasihreview.NavObjects.profilePage
import com.example.kasihreview.NavObjects.searchPage
import com.example.kasihreview.ViewModel.KRviewModel

@Composable
fun app(){
    val VM = viewModel<KRviewModel>()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val noHeaderRoutes = setOf(
        loginPage::class.qualifiedName,
        daftarPage::class.qualifiedName
    )
    Scaffold(
        topBar = {
            if (currentRoute !in noHeaderRoutes) {
                header()
            }
        },
        bottomBar = {
            if (currentRoute !in noHeaderRoutes) {
                bottomBar(navController)
            }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = loginPage,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable<loginPage>{
                loginPage(navController, VM)
            }
            composable<daftarPage>{
                daftarPage(navController, VM)
            }
            composable<HomePage>{
                homePage(navController,VM)
            }
            composable<MovieDetails>{
                movieDetails(navController,VM)
            }
            composable<searchPage>{
                searchPage(navController, VM)
            }
            composable<UlasanPage>{
                ulasanPage(navController, VM)
            }
            composable<profilePage>{
                profilePage(navController, VM)
            }
            composable<WatchListPage>{
                watchListPage(navController, VM)
            }
        }
    }
}