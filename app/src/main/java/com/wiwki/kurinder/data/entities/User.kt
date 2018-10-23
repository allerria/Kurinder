package com.wiwki.kurinder.data.entities

import java.util.*

data class User(
        var name: String = "",
        var birthDate: Date = Calendar.getInstance().time,
        var geolocation: Pair<Double, Double> = Pair(0.0, 0.0),
        var imageUrl: String = "",
        var isMale: Boolean = true,
        var desc: String = ""
)