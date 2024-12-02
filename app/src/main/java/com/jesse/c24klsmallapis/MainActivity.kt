package com.jesse.c24klsmallapis

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jesse.c24klsmallapis.presentation.SampleViewModel
import com.jesse.c24klsmallapis.ui.theme.C24kLSmallApisTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            C24kLSmallApisTheme {
                InitialScreen()
            }
        }
    }
}


@Composable
fun InitialScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("details") {
            DetailsScreen()
        }
    }
}

@Composable
fun DetailsScreen() {
}

@Composable
fun HomeScreen(viewmodel: SampleViewModel = hiltViewModel()) {
    val data by viewmodel.dataList.observeAsState(initial = emptyList())
    Log.d("TAG", "HomeScreen: $data ")
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data.size) {

            ListItem(
                modifier = Modifier.padding(10.dp),
                headlineContent = { Text(text = "${data[it].name}") },
                supportingContent = { Text(text = "${data[it].wikiUrl}") },
                tonalElevation = 10.dp,
                shadowElevation = 10.dp,
                leadingContent = {
                    AsyncImage(
                        model = data[it].image,
                        contentDescription = "image",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                    )
                }
            )
        }
    }
}

