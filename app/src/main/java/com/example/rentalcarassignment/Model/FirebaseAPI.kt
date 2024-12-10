package com.example.rentalcarassignment.Model

import retrofit2.http.Body
import retrofit2.http.POST

interface FirebaseAPI {


    @POST("/endpoint")
    suspend fun setMaxVehicleSpeed(
        @Body body: Vehicle
    )

}