package id.utdi.restaurantsapps.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Data class yang merepresentasikan entitas restoran
@Serializable
data class Restaurant(
    val id: String,          // ID unik restoran
    val name: String,        // Nama restoran
    val description: String, // Deskripsi restoran
    val pictureId: String,   // ID gambar restoran
    val city: String,        // Kota asal restoran
    val rating: Double       // Rating restoran
)
