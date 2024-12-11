package com.example.rentalcarassignment.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentalcarassignment.Model.FirebaseAPI
import com.example.rentalcarassignment.Model.Vehicle
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PushNotificationViewModel : ViewModel() {

    val TAG = javaClass.name
    private val BASE_URL = "www.some_test_url.com/vehicle_speed"

    private val api: FirebaseAPI =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()


    /*
     Description: method to perform api call
     via api object for setting max vehicle speed
     Dependency : Object : Vehicle
     */
    fun setVehicleMaxSpeed( vehicle: Vehicle){
        viewModelScope.launch {
            try {
                api.setMaxVehicleSpeed(vehicle)
            }catch (exception : Exception){
                Log.d(TAG , exception.toString())
            }

        }
    }
}