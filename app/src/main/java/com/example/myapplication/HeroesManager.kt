package com.example.myapplication

class HeroesManager {
    var pulHeroes: MutableMap<Int, Heroes> = mutableMapOf()

    fun createHero(){
        val newHero = Heroes()
        pulHeroes[pulHeroes.size+1] = newHero
    }

}