package id.utdi.restaurantsapps.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.utdi.restaurantsapps.RestaurantApplication
import id.utdi.restaurantsapps.data.RestaurantRepository
import id.utdi.restaurantsapps.model.Restaurant
import id.utdi.restaurantsapps.model.RestaurantResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Sealed interface yang merepresentasikan state UI untuk layar restoran
sealed interface RestaurantUiState {
    // State yang menandakan berhasil mendapatkan data restoran
    data class Success(val restaurant: RestaurantResponse) : RestaurantUiState
    // State yang menandakan terjadi kesalahan
    object Error : RestaurantUiState
    // State yang menandakan sedang dalam proses loading
    object Loading : RestaurantUiState
}

// Kelas ViewModel untuk layar restoran
class RestaurantViewModel(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    // Mutable state untuk menyimpan state UI saat ini
    var restaurantUiState: RestaurantUiState by mutableStateOf(RestaurantUiState.Loading)
        private set

    // Inisialisasi ViewModel
    init {
        // Memanggil fungsi untuk mendapatkan data restoran saat ViewModel diinisialisasi
        getRestaurant()
    }

    // Fungsi untuk mendapatkan data restoran
    fun getRestaurant() {
        viewModelScope.launch {
            // Mengubah state menjadi Loading sebelum mendapatkan data
            restaurantUiState = RestaurantUiState.Loading
            // Menggunakan try-catch untuk menangani kesalahan IO dan HTTP
            restaurantUiState = try {
                // Mendapatkan data restoran dari repository
                RestaurantUiState.Success(restaurantRepository.getRestaurant())
            } catch (e: IOException) {
                // Jika terjadi kesalahan IO, mengubah state menjadi Error
                RestaurantUiState.Error
            } catch (e: HttpException) {
                // Jika terjadi kesalahan HTTP, mengubah state menjadi Error
                RestaurantUiState.Error
            }
        }
    }

    companion object {
        // Factory untuk membuat instance ViewModel dengan dependencies
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Mendapatkan instance Application dari AndroidViewModelFactory
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as RestaurantApplication)
                // Mendapatkan repository dari container Application
                val restaurantRepository = application.container.restaurantRepository
                // Menginisialisasi dan mengembalikan instance RestaurantViewModel
                RestaurantViewModel(restaurantRepository = restaurantRepository)
            }
        }
    }
}
