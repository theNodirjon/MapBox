package com.example.mapbox

import android.Manifest.permission.*
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mapbox.databinding.ActivityMainBinding
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.attribution.attribution
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.locationcomponent.location2
import com.mapbox.maps.plugin.logo.logo
import com.mapbox.maps.plugin.scalebar.scalebar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestPermission()

        binding.mapView.getMapboxMap().loadStyleUri(Style.SATELLITE_STREETS, ) {
//            enableUserLocation()
        }

        binding.mapView.attribution.updateSettings {
            enabled = false
        }

        binding.mapView.logo.updateSettings {
            enabled = false
        }

        binding.mapView.scalebar.updateSettings {
            enabled = false
        }

        binding.mapView.compass.updateSettings {
            enabled = false
        }

    }

//     private fun enableUserLocation(){
//        binding.mapView.location2.updateSettings {
//            enabled=true
//            pulsingEnabled = true
//        }
//    }

    @SuppressLint("MissingPermission")
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    )
    { permissions ->
        Log.d("MainActivityTAG", ":${permissions} ")
        when {
            permissions.getOrDefault(ACCESS_FINE_LOCATION, false) -> {
//                enableUserLocation()
            }
            permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
//                enableUserLocation()
            }
            permissions.getOrDefault(ACCESS_BACKGROUND_LOCATION, false) -> {
//                enableUserLocation()
            }
            else -> {

            }
        }
    }

//    fun enableUserLocation() {
//        binding.mapView.location.updateSettings {
//            enabled = true
//            pulsingEnabled = true
//
//        }
//    }

    private fun requestPermission() {
        if (!PermissionsManager.areLocationPermissionsGranted(this)) {
            locationPermissionRequest.launch(
                arrayOf(
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION,
                )
            )
        }
    }

}

