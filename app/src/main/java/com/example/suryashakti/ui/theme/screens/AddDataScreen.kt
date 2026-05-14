package com.example.suryashakti.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ButtonDefaults


@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun AddDataScreen(

    navController: NavController,

    generatedPower: String,
    usagePower: String,

    onSaveData: (
        String,
        String,
        String
    ) -> Unit

)
{

    var generatedInput by remember {
        mutableStateOf("")
    }



    var usageInput by remember {
        mutableStateOf("")
    }

    var weatherCondition by remember {
        mutableStateOf("Sunny")
    }

    var expanded by remember {
        mutableStateOf(false)
    }



    Scaffold(
        containerColor = Color(0xFF121212),

        topBar = {

            CenterAlignedTopAppBar(

                title = {
                    Text(text = "Add Solar Data")
                } ,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                        containerColor = Color(0xFF121212),

                titleContentColor = Color(0xFFFFC107)
            ),
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)

        ) {

            OutlinedTextField(

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color(0xFFFFC107),

                    unfocusedBorderColor = Color.Gray,

                    focusedLabelColor = Color(0xFFFFC107),

                    unfocusedLabelColor = Color.LightGray,

                    cursorColor = Color(0xFFFFC107),

                    focusedTextColor = Color.White,

                    unfocusedTextColor = Color.White
                ),

                value = generatedInput,
                onValueChange = {
                    generatedInput = it
                },

                label = {
                    Text(text = "Generation Log (kWh)")
                },

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color(0xFFFFC107),

                    unfocusedBorderColor = Color.Gray,

                    focusedLabelColor = Color(0xFFFFC107),

                    unfocusedLabelColor = Color.LightGray,

                    cursorColor = Color(0xFFFFC107),

                    focusedTextColor = Color.White,

                    unfocusedTextColor = Color.White
                ),

                value = usageInput,
                onValueChange = {
                    usageInput = it
                },

                label = {
                    Text(text = "Consumption Log (kWh)")
                },

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,

                onExpandedChange = {
                    expanded = !expanded
                }
            ) {

                OutlinedTextField(

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor = Color(0xFFFFC107),

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = Color(0xFFFFC107),

                        unfocusedLabelColor = Color.LightGray,

                        cursorColor = Color(0xFFFFC107),

                        focusedTextColor = Color.White,

                        unfocusedTextColor = Color.White
                    ),

                    value = weatherCondition,

                    onValueChange = {},

                    readOnly = true,

                    label = {
                        Text(text = "Weather Condition")
                    },

                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                DropdownMenu(

                    expanded = expanded,

                    onDismissRequest = {
                        expanded = false
                    }

                ) {

                    DropdownMenuItem(

                        text = {
                            Text("Sunny")
                        },

                        onClick = {

                            weatherCondition = "Sunny"
                            expanded = false
                        }
                    )

                    DropdownMenuItem(

                        text = {
                            Text("Cloudy")
                        },

                        onClick = {

                            weatherCondition = "Cloudy"
                            expanded = false
                        }
                    )

                    DropdownMenuItem(

                        text = {
                            Text("Rainy")
                        },

                        onClick = {

                            weatherCondition = "Rainy"
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Spacer(modifier = Modifier.height(24.dp))

            Button(

                colors = ButtonDefaults.buttonColors(

                    containerColor = Color(0xFFFFC107),

                    contentColor = Color.Black
                ),

                onClick = {
                    onSaveData(
                        generatedInput,
                        usageInput,
                        weatherCondition
                    )

                    navController.popBackStack()

                },

                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Save Data"
                )
            }
        }
    }
}