package com.jesse.c24klsmallapis

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jesse.c24klsmallapis.domain.model.SampleModel
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
fun InitialScreen(viewModel: SampleViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel) {
                navController.navigate("details")
                viewModel.updateSelected(it)
            }
        }
        composable("details") {
            DetailsScreen(viewModel)
        }
    }
}

@Composable
fun DetailsScreen(viewModel: SampleViewModel) {
    val selected by viewModel.selected.observeAsState()
    val context = LocalContext.current
    Log.d("TAG", "DetailsScreen: $selected")
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = selected?.image,
            contentDescription = "image",
            modifier = Modifier.size(300.dp).padding(10.dp),
        )
        Text(text = selected?.name ?: "")
        Text(text = selected?.hair ?: "")
        Text(text = selected?.voicedBy ?: "")
        Button(onClick = { openLink(context, selected?.wikiUrl ?: "") }) {
            Text(text = "Go Link")
        }
    }
}
fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
@Composable
fun HomeScreen(viewmodel: SampleViewModel, onItemClick: (item: SampleModel) -> Unit) {
    val data by viewmodel.dataList.observeAsState(initial = emptyList())
    Log.d("TAG", "HomeScreen: $data ")
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data.size) {

            ListItem(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onItemClick(data[it]) },
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

