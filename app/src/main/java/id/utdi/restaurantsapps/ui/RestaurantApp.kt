package id.utdi.restaurantsapps.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import id.utdi.restaurantsapps.ui.screens.HomeScreen
import id.utdi.restaurantsapps.ui.screens.RestaurantViewModel
import id.utdi.restaurantsapps.ui.theme.RestaurantsAppsTheme

// Fungsi utama untuk menampilkan aplikasi restoran
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantApp() {
    // Menggunakan Scaffold untuk membangun tata letak utama aplikasi
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Menambahkan TopAppBar sebagai bagian atas aplikasi
            TopAppBar(
                title = {
                    Text(
                        "Restaurant Lists", // Judul TopAppBar
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        // Menggunakan Surface sebagai lapisan utama aplikasi
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Mendapatkan instance ViewModel menggunakan viewModel
            val restaurantViewModel: RestaurantViewModel =
                viewModel(factory = RestaurantViewModel.Factory)
            // Menampilkan layar utama dengan HomeScreen
            HomeScreen(
                restaurantUiState = restaurantViewModel.restaurantUiState,
                retryAction = restaurantViewModel::getRestaurant,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}

// Preview untuk LoadingScreen
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    RestaurantsAppsTheme {
        // Menggunakan Surface untuk menampilkan komponen RestaurantApp dalam preview
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Menampilkan komponen RestaurantApp dalam preview
            RestaurantApp()
        }
    }
}
