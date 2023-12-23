package id.utdi.restaurantsapps.data

import id.utdi.restaurantsapps.model.RestaurantResponse
import id.utdi.restaurantsapps.network.RestaurantApiService

// Interface yang mendefinisikan operasi yang dapat dilakukan pada data restoran
interface RestaurantRepository {
    // Mengambil data restoran secara asinkron
    suspend fun getRestaurant(): RestaurantResponse
}

// Implementasi default dari RestaurantRepository
class DefaultRestaurantRepository(
    private val restaurantApiService: RestaurantApiService
) : RestaurantRepository {
    // Fungsi untuk mengambil data restoran dari API
    override suspend fun getRestaurant(): RestaurantResponse {
        return try {
            // Memanggil fungsi dari layanan API untuk mendapatkan response restoran
            val response = restaurantApiService.getRestaurant()
            // Menambahkan logging untuk melihat response dari API
            println("Response: $response")
            // Mengembalikan response restoran
            response
        } catch (e: Exception) {
            // Menambahkan logging untuk melihat kesalahan jika terjadi
            println("Error: ${e.message}")
            // Melemparkan kembali kesalahan setelah melakukan logging
            throw e
        }
    }
}
