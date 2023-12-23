package id.utdi.restaurantsapps.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import id.utdi.restaurantsapps.network.RestaurantApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

// Interface yang mendefinisikan dependensi untuk App Container
interface AppContainer {
    val restaurantRepository: RestaurantRepository
}

// Implementasi default dari AppContainer
class DefaultAppContainer : AppContainer {
    // Base URL untuk API restoran
    private val BASE_URL = "https://restaurant-api.dicoding.dev/"

    // Logging interceptor untuk debugging panggilan jaringan
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Instansi Retrofit untuk membuat panggilan jaringan
    private val retrofit: Retrofit = Retrofit.Builder()
        // Menambahkan converter factory JSON untuk serialisasi/deserialisasi
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        // Menambahkan OkHttpClient dengan logging interceptor ke instansi Retrofit
        .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
        .build()

    // Layanan Retrofit yang diinisialisasi secara lazy untuk API Restoran
    private val retrofitService: RestaurantApiService by lazy {
        retrofit.create(RestaurantApiService::class.java)
    }

    // Repository Restoran yang diinisialisasi secara lazy menggunakan layanan Retrofit
    override val restaurantRepository: RestaurantRepository by lazy {
        DefaultRestaurantRepository(retrofitService)
    }
}
