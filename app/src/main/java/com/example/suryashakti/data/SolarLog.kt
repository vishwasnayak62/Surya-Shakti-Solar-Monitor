package com.example.suryashakti.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "solar_logs")
data class SolarLog(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val generatedPower: String,

    val usagePower: String,

    val weatherCondition: String,

    val savings: String,

    val co2Reduced: String,

    val gridStatus: String,

    val timestamp: String
)