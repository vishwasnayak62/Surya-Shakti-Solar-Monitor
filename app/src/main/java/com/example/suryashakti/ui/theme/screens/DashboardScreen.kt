package com.example.suryashakti.ui.theme.screens



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.suryashakti.ui.theme.components.DashboardCard
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.navigation.NavController
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,

    generatedPower: String,
    usagePower: String,
    savingsAmount: String,
    co2Reduction: String,
    weatherStatus: String,
    gridStatus: String,


) {


    val smartSuggestion =

        when (weatherStatus) {

            "Sunny" ->

                "☀️ High Solar Output\nBest time to run heavy appliances."

            "Cloudy" ->

                "⛅ Moderate Solar Availability\nUse appliances efficiently."

            "Rainy" ->

                "🌧️ Low Solar Production\nGrid power may be required."

            else ->

                "Monitoring Solar Conditions"
        }

    val generatedValue =

        generatedPower
            .replace("kWh", "")
            .trim()
            .toFloatOrNull()

            ?: 0f

    val usageValue =

        usagePower
            .replace("kWh", "")
            .trim()
            .toFloatOrNull()

            ?: 0f

    val weatherEfficiency = when (weatherStatus) {

        "Sunny" -> 1.0f

        "Cloudy" -> 0.7f

        "Rainy" -> 0.4f

        else -> 1.0f
    }

    val effectiveGeneration =
        generatedValue * weatherEfficiency



    val solarRatio =

        if (usageValue > 0) {

            ((effectiveGeneration / usageValue) * 100)
                .coerceIn(0f, 100f)

        } else {

            0f
        }
    val progressValue =
        solarRatio / 100f

    val currentHour =

        Calendar.getInstance()
            .get(Calendar.HOUR_OF_DAY)

    val greetingMessage = when {

        currentHour in 0..11 ->
            "Good Morning ☀️"

        currentHour in 12..16 ->
            "Good Afternoon 🌤️"

        currentHour in 17..20 ->
            "Good Evening 🌇"

        else ->
            "Good Night 🌙"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF121212),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = Color(0xFF121212),

                    titleContentColor = Color(0xFFFFC107)
                ),
                title = {
                    Text(text = "☀️ Surya-Shakti")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(
                text = greetingMessage,
                color = Color.White
            )

            Text(
                text = "User",
                color = Color.White
            )



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),

                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(

                    progress = { progressValue },

                    modifier = Modifier.size(220.dp),

                    strokeWidth = 20.dp,

                    color = Color(0xFFFFC107),

                    trackColor = Color.DarkGray
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "☀️",
                        fontSize = 40.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Solar vs Grid",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "${solarRatio.toInt()}%",
                        color = Color(0xFFFFC107),
                        fontSize = 34.sp
                    )
                }
            }





            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFC107)
                ),

                shape = RoundedCornerShape(20.dp)

            ) {

                Column(

                    modifier = Modifier.padding(20.dp)

                ) {

                    Text(

                        text = "Smart Suggestion",

                        fontSize = 18.sp,

                        fontWeight = FontWeight.Bold,

                        color = Color.Black

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(

                        text = smartSuggestion,

                        color = Color.Black,

                        fontSize = 16.sp,

                        lineHeight = 24.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                DashboardCard(
                    title = "Generated",
                    value = generatedPower,
                    modifier = Modifier.weight(1f)
                )

                DashboardCard(
                    title = "Usage",
                    value = usagePower,
                    modifier = Modifier.weight(1f)
                )
            }



            Row(
                modifier = Modifier.fillMaxWidth()
            ) {



                DashboardCard(
                    title = "Savings",
                    value = savingsAmount,
                    modifier = Modifier.weight(1f)
                )

                DashboardCard(
                    title = "CO₂ Reduced",
                    value = co2Reduction,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                DashboardCard(
                    title = "Grid Status",
                    value = gridStatus,
                    modifier = Modifier.weight(1f)
                )

                DashboardCard(
                    title = "Weather",
                    value = weatherStatus,
                    modifier = Modifier.weight(1f)
                )
            }


            Button(

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107),
                    contentColor = Color.Black
                ),
                onClick = {

                    navController.navigate("add_data")

                },

                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Go To Add Data"
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button(

                onClick = {

                    navController.navigate("history")

                },

                colors = ButtonDefaults.buttonColors(

                    containerColor = Color.DarkGray,

                    contentColor = Color.White
                ),

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(
                    text = "View History"
                )
            }
        }
    }
}