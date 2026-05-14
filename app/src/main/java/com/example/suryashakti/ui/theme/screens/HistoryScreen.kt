package com.example.suryashakti.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.suryashakti.data.SolarDao
import com.example.suryashakti.data.SolarLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(

    navController: NavController,

    solarDao: SolarDao
) {

    val logs = remember {
        mutableStateListOf<SolarLog>()
    }

    LaunchedEffect(Unit) {

        val savedLogs =
            solarDao.getAllLogs()

        logs.clear()

        logs.addAll(savedLogs)
    }

    Scaffold(

        containerColor = Color(0xFF121212),

        topBar = {

            CenterAlignedTopAppBar(

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = Color(0xFF121212),

                    titleContentColor = Color(0xFFFFC107)
                ),

                title = {

                    Text(
                        text = "Solar History"
                    )
                }
            )
        }

    ) { innerPadding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            items(logs) { log ->

                Card(

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    ),

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Column(

                        modifier = Modifier.padding(16.dp)

                    ) {

                        Text(

                            text = "Weather: ${log.weatherCondition}",

                            color = Color(0xFFFFC107),

                            fontSize = 18.sp
                        )

                        Text(

                            text = log.timestamp,

                            color = Color.LightGray,

                            fontSize = 14.sp
                        )

                        Text(
                            text = "Generated: ${log.generatedPower}",
                            color = Color.White
                        )

                        Text(
                            text = "Usage: ${log.usagePower}",
                            color = Color.White
                        )

                        Text(
                            text = "Savings: ${log.savings}",
                            color = Color.White
                        )

                        Text(
                            text = "CO₂ Reduced: ${log.co2Reduced}",
                            color = Color.White
                        )

                        Text(
                            text = "Grid Status: ${log.gridStatus}",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}