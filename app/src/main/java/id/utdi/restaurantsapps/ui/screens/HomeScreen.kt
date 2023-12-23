package id.utdi.restaurantsapps.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.utdi.restaurantsapps.R
import id.utdi.restaurantsapps.model.Restaurant
import id.utdi.restaurantsapps.ui.theme.RestaurantsAppsTheme

// Fungsi untuk menampilkan layar utama aplikasi
@Composable
fun HomeScreen(
    restaurantUiState: RestaurantUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // Menentukan tampilan berdasarkan status UI State
    when (restaurantUiState) {
        is RestaurantUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is RestaurantUiState.Success ->
            RestaurantListScreen(
                restaurant = restaurantUiState.restaurant.restaurants,
                modifier = modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    ),
                contentPadding = contentPadding
            )
        else -> ErrorScreen(retryAction, modifier)
    }
}

// Fungsi untuk menampilkan layar loading
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading),
        contentDescription = "Loading",
        modifier = modifier
    )
}

// Fungsi untuk menampilkan layar error
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gagal Memuat Data")
        Button(onClick = retryAction) {
            Text("Coba Lagi")
        }
    }
}

// Fungsi untuk menampilkan kartu restoran
@Composable
fun RestaurantCard(restaurant: Restaurant, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary
            )
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://restaurant-api.dicoding.dev/images/small/" + restaurant.pictureId)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.broken_img),
                    placeholder = painterResource(id = R.drawable.loading)
                )
            }
            Text(
                text = restaurant.description.take(150), // Membatasi panjang deskripsi
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "City: ${restaurant.city}",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFF000000)
                )
                Text(
                    text = "Rating: ${restaurant.rating}",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFFFFD700)
                )
            }
        }
    }
}

// Fungsi untuk menampilkan daftar restoran
@Composable
private fun RestaurantListScreen(
    restaurant: List<Restaurant>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = restaurant,
            key = { restaurant ->
                restaurant.name
            }
        ) { restaurant ->
            RestaurantCard(restaurant = restaurant, modifier = Modifier.fillMaxSize())
        }
    }
}

// Preview untuk layar loading
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    RestaurantsAppsTheme() {
        val mockData = List(10) {
            Restaurant(
                "Lorem Ipsum - $it",
                "$it",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                        " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
                        " minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                        " ex ea commodo consequat.",
                "14",
                "Medan",
                4.2
            )
        }
        RestaurantListScreen(mockData, Modifier.fillMaxSize())
    }
}
