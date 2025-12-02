package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.NavObjects.profilePage
import com.example.kasihreview.NavObjects.searchPage
import com.example.kasihreview.R

data class BottomNavBar(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
@Composable

fun bottomBar(navController: NavController){

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    val botBarItems = listOf(
        BottomNavBar(
            "Home",
            ImageVector.vectorResource(R.drawable.home__1_),
            ImageVector.vectorResource(R.drawable.home__1_),
            false
        ),
        BottomNavBar(
            "Search",
            ImageVector.vectorResource(R.drawable.search),
            ImageVector.vectorResource(R.drawable.search),
            false
        ),
        BottomNavBar(
            "Profile",
            ImageVector.vectorResource(R.drawable.user),
            ImageVector.vectorResource(R.drawable.user),
            false
        )
    )

    NavigationBar(
        containerColor = Color(0xFF1F1D36)
    ) {
        botBarItems.forEachIndexed { index, bottomNavBar ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    if (selectedIndex == 0) {
                        navController.navigate(HomePage)
                    }else if (selectedIndex == 1) {
                        navController.navigate(searchPage)
                    }else if (selectedIndex == 2) {
                        navController.navigate(profilePage)
                    }
                },
                label = {
                    //Text(text = bottomNavBar.title)
                },
                icon = {
                    if (selectedIndex == index) {
                        Icon(
                            imageVector = bottomNavBar.selectedIcon,
                            contentDescription = bottomNavBar.title
                        )
                    }else {
                        Icon(
                            imageVector = bottomNavBar.unselectedIcon,
                            contentDescription = bottomNavBar.title
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFE9A6A6),               // warna icon ketika dipilih
                    unselectedIconColor = Color.White.copy(0.6f),  // warna icon ketika tidak dipilih
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White.copy(0.6f),
                    indicatorColor = Color.Transparent             // 🔥 ini yang penting: hilangkan background
                )
            )
        }
    }
}