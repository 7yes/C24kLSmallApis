package com.jesse.c24klsmallapis.presentaion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jesse.c24klsmallapis.domain.model.SmModel
import com.jesse.c24klsmallapis.presentaion.uistate.UIState
import com.jesse.c24klsmallapis.ui.theme.C24kLSmallApisTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            C24kLSmallApisTheme {
                InitScreen()
            }
        }
    }
}

@Composable
fun InitScreen() {

    val navController = rememberNavController()
    lateinit var itemSlected: SmModel
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen() {
                navController.navigate("detail")
                itemSlected = it
            }
        }
        composable("detail") {
            DetailScreen(itemSlected)
        }
    }
}

@Composable
fun HomeScreen(viemModel: SMViewModel = hiltViewModel(), onclikListener: (SmModel) -> Unit) {
    val state = viemModel.uiState.collectAsState()

    when (state.value) {
        is UIState.Error -> ErrorScreen((state.value as UIState.Error).error)
        UIState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }

        is UIState.Success -> {
            val list = (state.value as UIState.Success).mySuccessList
            LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 24.dp)) {
                items(list) { item ->
                    ItemView(item, onclikListener)
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = error)
    }
}

fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Composable
fun DetailScreen(item: SmModel) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = item.image, modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(24.dp),
            contentDescription = "Image", alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        MyText("Name: ${item.name.toString()}", Color.Blue, Color.White)
        MyText("Age: ${item.age.toString()}", Color.Blue, Color.White)
        MyText("First Episode: ${item.firstEpisode.toString()}", Color.Blue, Color.White)
        Button(onClick = { openLink(context,item.wikiUrl.toString())}, modifier = Modifier.padding(24.dp)) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun ItemView(item: SmModel, onClickListener: (SmModel) -> Unit) {
    Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Green, shape = RoundedCornerShape(0, 24, 0, 24))
            .clickable { onClickListener(item) }
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = item.image, modifier = Modifier.size(150.dp),
            contentDescription = "Image", alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        MyText(item.name.toString())
    }
}

@Composable
fun MyText(
    text: String = "",
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Black
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor.copy(alpha = 0.5f)),
        color = textColor, textAlign = TextAlign.Center, maxLines = 1,
        text = text, fontSize = 26.sp
    )
}
