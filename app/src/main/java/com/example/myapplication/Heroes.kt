package com.example.myapplication

import kotlin.random.Random

open class Heroes() {
    var rarity: String? = null
    var baseCharacter: String? = null
    var intelligence: Int? = null
    var power: Int? = null
    var culture: Int? = null
    var ratingHero: Int? = null
    init {
        val userRarityPair = mapOf("common" to 1.0, "uncommon" to 1.0, "rare" to 1.0, "mythic" to 1.0, "immortal" to 1.0).toList()
        val selectUserRarityPair = userRarityPair[Random.nextInt(0, userRarityPair.size)]
        this.rarity = selectUserRarityPair.first
        val baseCharacterIndex: Double = selectUserRarityPair.second
        this.baseCharacter = listOf("intelligence", "power", "culture")[Random.nextInt(0, 3)]
        this.intelligence= (Random.nextInt(2, 10) * baseCharacterIndex).toInt()
        this.power = (Random.nextInt(2, 10) * baseCharacterIndex).toInt()
        this.culture = (Random.nextInt(2, 10) * baseCharacterIndex).toInt()
        this.ratingHero = intelligence!! + power!! + culture!!
    }
}