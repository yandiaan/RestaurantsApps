package id.utdi.restaurantsapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import id.utdi.restaurantsapps.ui.RestaurantApp
import id.utdi.restaurantsapps.ui.theme.RestaurantsAppsTheme

// Kelas utama yang mewarisi ComponentActivity
class MainActivity : ComponentActivity() {
    // Fungsi onCreate yang dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menetapkan konten aktivitas menggunakan setContent
        setContent {
            // Menggunakan RestaurantsAppsTheme untuk menetapkan tema aplikasi
            RestaurantsAppsTheme {
                // Menggunakan Surface sebagai lapisan utama aplikasi
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Menampilkan komponen RestaurantApp sebagai konten utama
                    RestaurantApp()
                }
            }
        }
    }
}
