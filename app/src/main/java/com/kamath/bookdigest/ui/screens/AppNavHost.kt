package com.kamath.bookdigest.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kamath.bookdigest.ui.screens.common.BookDetailsScreen
import com.kamath.bookdigest.utility.BarcodeScanner
import com.kamath.bookdigest.utility.ScanBarcode

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val barcodeScanner = BarcodeScanner(LocalContext.current)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("scan") {
            ScanBarcode(
                onScanBarcode = { barcodeScanner.startScan() },
                barcodeValue = barcodeScanner.barCodeResults.collectAsStateWithLifecycle().value
            )
        }
        composable("account") {
            AccountScreen()
        }
        composable("bookDetails/{isbn}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("isbn")
            BookDetailsScreen(navController,bookId = bookId)
        }
    }
}