package id.utdi.restaurantsapps.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Data class yang merepresentasikan respons dari API restoran
@Serializable
data class RestaurantResponse(
    val error: Boolean,             // Indikator apakah terdapat kesalahan dalam respons
    val message: String,            // Pesan yang menyertai respons
    val count: Int,                 // Jumlah total restoran dalam respons
    @SerialName("restaurants")      // SerialName untuk mencocokkan nama properti dengan API
    val restaurants: List<Restaurant> // Daftar restoran yang terdapat dalam respons
)
