package id.utdi.restaurantsapps

import android.app.Application
import id.utdi.restaurantsapps.data.AppContainer
import id.utdi.restaurantsapps.data.DefaultAppContainer

// Kelas Application yang merupakan entry point aplikasi
class RestaurantApplication : Application() {
    // Property untuk menyimpan instance AppContainer
    lateinit var container: AppContainer

    // Fungsi onCreate yang dipanggil saat aplikasi pertama kali dibuat
    override fun onCreate() {
        super.onCreate()
        // Inisialisasi container dengan DefaultAppContainer
        container = DefaultAppContainer()
    }
}
