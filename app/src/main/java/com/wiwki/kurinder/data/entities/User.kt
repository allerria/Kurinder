package com.wiwki.kurinder.data.entities

data class User(
        var name: String = "",
        var birthDate: Long = 0,
        var geolocation: Pair<Double, Double> = Pair(0.0, 0.0),
        var imageUrl: String = "",
        var isMale: Boolean = true,
        var desc: String = ""
)