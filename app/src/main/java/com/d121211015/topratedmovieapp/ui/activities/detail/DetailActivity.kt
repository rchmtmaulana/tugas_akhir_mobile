package com.d121211015.topratedmovieapp.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211015.topratedmovieapp.data.models.TopMovie
import com.d121211015.topratedmovieapp.ui.theme.TopRatedMovieAppTheme

class DetailActivity : ComponentActivity() {

    private var selectedMovie: TopMovie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedMovie = intent.getParcelableExtra("MOVIE")
        setContent {
            TopRatedMovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    fun DetailScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier
                .size(width = 480.dp, height = 240.dp)
            ){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
                ){
                    // Movie Backdrop
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data("https://image.tmdb.org/t/p/original" + selectedMovie?.backdrop_path)
                            .crossfade(true)
                            .build(), contentDescription = selectedMovie?.title,
                        contentScale = ContentScale.Crop
                    )

                    Box(modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(topStart = 16.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                    ){
                        Text(text = selectedMovie?.vote_average.toString().take(3),
                            style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                    }
                }
                Box(modifier = Modifier
                    .offset(y = 150.dp)
                    .width(120.dp)
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.small)
                ){
                    // Movie Poster
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data("https://image.tmdb.org/t/p/original" + selectedMovie?.poster_path)
                            .crossfade(true)
                            .build(), contentDescription = selectedMovie?.title,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Movie Details
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = selectedMovie?.title.toString(), style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Release Date : ${selectedMovie?.release_date.toString()}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Vote Count : ${selectedMovie?.vote_count.toString()}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Popularity: ${selectedMovie?.popularity.toString().take(2)}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Overview", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
            Text(text = selectedMovie?.overview.toString(), style = MaterialTheme.typography.titleLarge)
        }
    }
}