package com.example.myapplication

class TownManager {
    var intelligence: Int = 0
    private var dIntelligence: Int = 0
    var power: Int = 0
    private var dPower: Int = 0
    var culture: Int = 0
    var dCulture: Int = 0
    var ratingTown: Int = 0
    private var pulHeroes: MutableMap<Int, Heroes> = mutableMapOf()

    public fun addHero(Hero: Heroes) {
        this.pulHeroes[pulHeroes.size] = Hero
        this.intelligence += Hero.intelligence
        this.power += Hero.power
        this.culture += Hero.culture

        this.dIntelligence = this.intelligence / this.pulHeroes.size
        this.dPower = this.power / this.pulHeroes.size
        this.dCulture = this.culture / this.pulHeroes.size
    }
}