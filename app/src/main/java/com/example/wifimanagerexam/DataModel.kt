package com.example.wifimanagerexam

class DataModel {
    data class Wifi(
        val ssid: String,
        val address: String,
        val level: Int?,
        val capabilities: String?,
        val frequency: Int
    )
}