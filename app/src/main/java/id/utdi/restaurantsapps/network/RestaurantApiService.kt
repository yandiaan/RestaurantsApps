package id.utdi.restaurantsapps.network

import id.utdi.restaurantsapps.model.Restaurant
import id.utdi.restaurantsapps.model.RestaurantResponse
import retrofit2.http.GET

// Interface yang mendefinisikan endpoint-endpoint API restoran
interface RestaurantApiService {
    // Mendefinisikan endpoint untuk mendapatkan daftar restoran secara asinkron
    @GET("list")
    suspend fun getRestaurant(): RestaurantResponse
}
