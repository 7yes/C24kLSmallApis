package com.jesse.c24klsmallapis.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    NavHost( navController = navController, startDestination = "home" ){
        composable("home"){
            HomeScreen()
        }
        composable("detail"){
            DetailScreen()
        }
    }
}

@Composable
fun HomeScreen(viemModel: SMViewModel = hiltViewModel()) {
    val state = viemModel.uiState.collectAsState()

    when(state.value){
        is UIState.Error -> ErrorScreen((state.value as UIState.Error).error)
        UIState.Loading -> {
            Box (Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
               CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }
        is UIState.Success -> {}
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box (Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = error)
    }
}

@Composable
fun DetailScreen() {

}
