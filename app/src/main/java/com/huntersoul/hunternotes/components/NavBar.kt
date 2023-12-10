package com.huntersoul.hunternotes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.huntersoul.hunternotes.navigation.BottomNavigationItem
import com.huntersoul.hunternotes.R

@Composable
fun NavBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination =  navBackStackEntry?.destination

        val listOfNavItems = listOf(
            BottomNavigationItem(
                title = "Notas",
                selectedIcon = ImageVector.vectorResource(R.drawable.notahome),
                unselectedIcon = ImageVector.vectorResource(R.drawable.notahome),
            ),
            BottomNavigationItem(
                title = "Tareas",
                selectedIcon = ImageVector.vectorResource(R.drawable.tarea),
                unselectedIcon = ImageVector.vectorResource(R.drawable.tarea),
            ),
            BottomNavigationItem(
                title = "MarsApi",
                selectedIcon = ImageVector.vectorResource(R.drawable.baseline_wifi),
                unselectedIcon = ImageVector.vectorResource(R.drawable.baseline_wifi),
        )
        )

        listOfNavItems.forEach{ navItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == navItem.title } == true,
                onClick = {
                    navController.navigate(navItem.title){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(navItem.title)
                },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector =
                        if (currentDestination?.hierarchy?.any { it.route == navItem.title } == true)
                            navItem.selectedIcon
                        else navItem.unselectedIcon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}