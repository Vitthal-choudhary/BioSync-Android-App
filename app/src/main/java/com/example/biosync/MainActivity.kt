package com.example.biosync

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect


import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biosync.ui.theme.AppTheme
import com.example.biosync.ui.theme.primaryDark
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LaunchedEffect(key1 = true) {
                    delay(1500) // Delay for 3 seconds
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Scaffold(
                    topBar = {
                        TopAppBar(modifier = Modifier.fillMaxWidth(),
                            title = {
                                Box(modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center){
                                    Text("BioSync",
                                        color = Color.Black,
                                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(top=10.dp)
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = primaryDark
                            ))
                    },
                    modifier = Modifier.fillMaxSize(),
                    containerColor = primaryDark
                ) { innerPadding ->
                    Splash(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}