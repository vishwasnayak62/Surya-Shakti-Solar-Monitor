package com.example.suryashakti.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SolarDao {

    @Insert
    suspend fun insertLog(
        solarLog: SolarLog
    )

    @Query(
        "SELECT * FROM solar_logs ORDER BY id DESC"
    )
    suspend fun getAllLogs(): List<SolarLog>

    @Query(
        "SELECT * FROM solar_logs ORDER BY id DESC LIMIT 1"
    )
    suspend fun getLatestLog(): SolarLog?
}