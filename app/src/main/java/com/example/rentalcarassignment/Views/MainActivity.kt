package com.example.rentalcarassignment.Views

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rentalcarassignment.Manifest
import com.example.rentalcarassignment.Model.Vehicle
import com.example.rentalcarassignment.R
import com.example.rentalcarassignment.ViewModels.PushNotificationViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

   lateinit var setSpeedButton : Button
   lateinit  var vehicleSpeedViewModel : ViewModel
   lateinit var vehicle :Vehicle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        initUI()
        initData()

        vehicleSpeedViewModel = ViewModelProvider(this).get(PushNotificationViewModel::class.java)

        setSpeedButton.setOnClickListener {
            lifecycleScope.launch {
                (vehicleSpeedViewModel as PushNotificationViewModel).setVehicleMaxSpeed(vehicle)
            }
        }

    }

    private fun initData() {
        vehicle = Vehicle(1234, "car", 23, 80)
        // TODO : add user input values
    }

    private fun initUI() {
        setSpeedButton = findViewById(R.id.setSpeedButton)
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val isPermissionGranted = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (isPermissionGranted) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }

        }

    }

}