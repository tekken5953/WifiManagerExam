package com.example.wifimanagerexam

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

@SuppressLint("MissingPermission")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()
        val wifiManager = application.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val result: List<ScanResult> = wifiManager.scanResults

        val btnScan: Button = findViewById(R.id.btnScan)
        btnScan.setOnClickListener {
            result.forEach {
                Log.i("WIFI_RESULT", "SSID : ${it.SSID} BSSID : ${it.BSSID}")
            }
        }
    }

    private fun requestPermission() {
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            val permissions = arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }
}