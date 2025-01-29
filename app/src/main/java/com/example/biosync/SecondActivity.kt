package com.example.biosync

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity(){
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val showNothinG = remember { mutableStateOf(false) }
            val selectedItem = remember { mutableStateOf(0) }
            val items = listOf("Dashboard", "Updates")
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "Hello User", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                                    Image(
                                        painter = painterResource(id = R.drawable.avatar),
                                        contentDescription = "User Avatar",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(CircleShape)
                                    )
                                }
                            }
                        })
                },
                bottomBar = {
                    BottomAppBar(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color.Transparent)
                            .fillMaxWidth(),
                        containerColor = Color.Transparent,
                    ) {
                        NavigationBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            containerColor = Color.Transparent
                        ) {
                            items.forEachIndexed { index, item ->
                                var icon = Icons.Filled.Home
                                var label = "Dashboard"
                                if (index == 1) {
                                    icon = Icons.Filled.Notifications
                                    label = "Updates"
                                }
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = label,
                                            modifier = Modifier
                                                .size(30.dp)
                                                .padding(4.dp)
                                        )
                                    },
                                    label = { Text(item) },
                                    selected = selectedItem.value == index,
                                    onClick = {
                                        selectedItem.value = index
                                        if (index == 1)
                                            showNothinG.value = true
                                        else
                                            showNothinG.value=false
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Color.White,
                                        selectedTextColor = Color.White,
                                        indicatorColor = Color.Blue,
                                        unselectedIconColor = Color.Gray,
                                        unselectedTextColor = Color.Gray
                                    ),
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .background(
                                            if (selectedItem.value == index) {
                                                Color.Blue
                                            } else {
                                                Color.Transparent
                                            },
                                            RoundedCornerShape(20.dp)
                                        )
                                        .padding(8.dp),
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (selectedItem.value == 0){
                        CardsPreview()
                    }
                    else if(showNothinG.value){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    Icons.Filled.Notifications,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(20.dp)
                                )
                                Text(text = "No New Notifications", fontSize = 30.sp, modifier = Modifier
                                    .padding(20.dp)
                                )
                            }

                        }
                    }

                }
            }
        }

    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun Cards(imageIcon: @Composable () -> Unit, heading: String, body: String) {
    Card(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,

        ) {
            Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
                imageIcon()
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = heading, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()){
            }
            Text(text = body, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun Buttonsforuser(icon: @Composable () -> Unit, label: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        IconButton(
            onClick = {
            },
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    shape = CircleShape
                )        ) {
            icon()
        }
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardsPreview(){
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Cards(imageIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.thermometer),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(2.dp)
                        .width(30.dp)
                        .height(30.dp)
                )
            }, heading = "Temperature", body = "Your body temperature is normal")
            Cards(imageIcon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(2.dp)
                        .width(30.dp)
                        .height(30.dp)
                )
            }, heading = "Heart rate", body = "Your heart rate is normal")
            Cards(imageIcon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(2.dp)
                        .width(30.dp)
                        .height(30.dp)
                )
            }, heading = "Blood Oxygen", body = "Your blood oxygen is normal")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            Buttonsforuser(
                icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
                label = "Schedule an Appointment"
            )
            Buttonsforuser(
                icon = { Icon(Icons.Filled.Share, contentDescription = null) },
                label = "Share Your Report"
            )
        }
    }
}

