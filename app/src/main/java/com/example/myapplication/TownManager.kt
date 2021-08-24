package com.example.myapplication

class TownManager {
    var intelligence: Int = 0
    private var dIntelligence: Int? = null
    var power: Int = 0
    private var dPower: Int? = null
    var culture: Int = 0
    var dCulture: Int? = null
    var ratingTown: Int = 0
    private var pulHeroes: MutableMap<Int, Heroes> = mutableMapOf()
}