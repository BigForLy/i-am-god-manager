package com.example.myapplication

import android.graphics.Bitmap

class HeroesManager(private val mutableBitmap: Bitmap) {
    private val imageHeroDrawable = ImageHeroDrawable()

    fun newHero(): Heroes{
        val hero = Heroes()
        hero.imageHero = imageHeroDrawable.create(mutableBitmap, hero)
        return hero
    }
}