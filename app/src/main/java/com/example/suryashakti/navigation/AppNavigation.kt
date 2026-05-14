package com.example.suryashakti.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.suryashakti.ui.theme.screens.AddDataScreen
import com.example.suryashakti.ui.theme.screens.DashboardScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.suryashakti.data.SolarDatabase
import com.example.suryashakti.data.SolarLog
import androidx.compose.runtime.LaunchedEffect
import com.example.suryashakti.ui.theme.screens.HistoryScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun AppNavigation() {

    var generatedPower by remember {
        mutableStateOf("15 kWh")
    }

    var batteryLevel by remember {
        mutableStateOf("78%")
    }

    var usagePower by remember {
        mutableStateOf("10 kWh")
    }

    var savingsAmount by remember {
        mutableStateOf("₹120")
    }

    var co2Reduction by remember {
        mutableStateOf("0 kg")
    }

    var weatherStatus by remember {
        mutableStateOf("Sunny")
    }

    var gridStatus by remember {
        mutableStateOf("Normal")
    }



    val navController = rememberNavController()

    val context = LocalContext.current

    val database =
        SolarDatabase.getDatabase(context)

    val solarDao =
        database.solarDao()

    val coroutineScope =
        rememberCoroutineScope()

    LaunchedEffect(Unit) {

        val latestLog =
            solarDao.getLatestLog()

        latestLog?.let {

            generatedPower = it.generatedPower

            usagePower = it.usagePower

            weatherStatus = it.weatherCondition

            savingsAmount = it.savings

            co2Reduction = it.co2Reduced

            gridStatus = it.gridStatus
        }
    }



    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(
                navController = navController,

                generatedPower = generatedPower,
                usagePower = usagePower,
                savingsAmount = savingsAmount,
                co2Reduction = co2Reduction,
                weatherStatus = weatherStatus,
                gridStatus = gridStatus,




            )
        }

        composable("add_data") {
            AddDataScreen(

                navController = navController,

                generatedPower = generatedPower,

                usagePower = usagePower,

                onSaveData = { generated, usage, weather ->

                    generatedPower = "$generated kWh"

                    usagePower = "$usage kWh"

                    weatherStatus = weather

                    val generatedValue = generated.toFloatOrNull() ?: 0f

                    val weatherEfficiency = when (weather) {

                        "Sunny" -> 1.0f

                        "Cloudy" -> 0.7f

                        "Rainy" -> 0.4f

                        else -> 1.0f
                    }

                    val usageValue = usage.toFloatOrNull() ?: 0f

                    val effectiveGeneration =
                        generatedValue * weatherEfficiency

                    val independenceCalculated =

                        if (usageValue > 0) {

                            ((effectiveGeneration / usageValue) * 100)
                                .coerceIn(0f, 100f)

                        } else {

                            0f
                        }

                    val batteryCalculated =

                        if (effectiveGeneration >= usageValue) {

                            100f

                        } else {

                            ((effectiveGeneration / usageValue) * 100)
                                .coerceIn(0f, 100f)
                        }

                    val savingsCalculated =
                        effectiveGeneration * 6

                    val co2Calculated =
                        effectiveGeneration * 0.4f

                    gridStatus =

                        if (effectiveGeneration > usageValue) {

                            "Exporting to Grid"

                        } else if (effectiveGeneration < usageValue) {

                            "Using Grid Backup"

                        } else {

                            "Balanced Usage"
                        }



                    batteryLevel =
                        "${batteryCalculated.toInt()}%"

                    savingsAmount =
                        "₹${savingsCalculated.toInt()}"

                    co2Reduction =
                        "${co2Calculated.toInt()} kg"

                    coroutineScope.launch {

                        val currentTime = SimpleDateFormat(

                            "dd MMM yyyy • hh:mm a",

                            Locale.getDefault()

                        ).format(Date())

                        val solarLog = SolarLog(

                            generatedPower = generatedPower,

                            usagePower = usagePower,

                            weatherCondition = weather,

                            savings = savingsAmount,

                            co2Reduced = co2Reduction,

                            gridStatus = gridStatus,

                            timestamp = currentTime
                        )

                        solarDao.insertLog(solarLog)
                    }
                }
            )
        }
        composable("history") {

            HistoryScreen(
                navController = navController,
                solarDao = solarDao
            )
        }
    }
}