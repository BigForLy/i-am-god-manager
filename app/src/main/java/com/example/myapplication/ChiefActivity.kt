package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat.setTranslationX
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.widget.Button
import android.widget.ImageView


class ChiefActivity : AppCompatActivity() {
    private var dX = 0.0f
    private var dY = 0.0f
    private var startPositionX = 0.0f
    private var startPositionY = 0.0f
    private var downBorder = 0.0f
    private var heroesManager = HeroesManager()
    private var townManager = TownManager()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_chief)

        setChefActivityButtonListener()
        setOnTouchImageListener()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchImageListener() {
        val imageViewComponent = findViewById<ImageView>(R.id.imageView2)

        imageViewComponent.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val resourcesWidth = resources.displayMetrics.widthPixels
                val resourcesHeight = resources.displayMetrics.heightPixels - downBorder
                when (event.action) {
                    ACTION_DOWN -> {
                        dX = event.rawX - v.x
                        dY = event.rawY - v.y
                        startPositionX = v.x
                        startPositionY = v.y
                        return true
                    }
                    ACTION_MOVE -> {
                        val dXAnimate =
                            when {
                                event.rawX - dX <= 0.0f -> 0.0f
                                event.rawX - dX + v.width >= resourcesWidth -> (resourcesWidth - v.width).toFloat()
                                else -> event.rawX - dX
                            }
                        val dYAnimate =
                            when {
                                event.rawY - dY <= 0.0f -> 0.0f
                                event.rawY - dY + v.height >= resourcesHeight -> (resourcesHeight - v.height).toFloat()
                                else -> event.rawY - dY
                            }
                        v.animate()
                            .x(dXAnimate)
                            .y(dYAnimate)
                            .setDuration(0)
                            .start()
                        return true
                    }
                    ACTION_UP -> {
                        when {
                            v.x > startPositionX + resourcesWidth/7 -> {println("right")}
                            v.x < startPositionX - resourcesWidth/7 -> {println("left")}
                           else -> {println("none")}
                        }
                        v.animate()
                            .x(startPositionX)
                            .y(startPositionY)
                            .setDuration(0)
                            .start()
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }
        })

    }

    private fun setChefActivityButtonListener() {
        val button3 = findViewById<Button>(R.id.button3)

        button3.setOnClickListener {
            val userPreferences = UserSettingsStorage(this)
            userPreferences.save(userPreferences.appPreferencesConnection, "True")
            val backToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(backToMainActivity)
        }

        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            val imageView = findViewById<ImageView>(R.id.imageView2)
            imageView.setImageDrawable(ContextCompat.getDrawable(
                applicationContext,
                R.drawable.hero_test2
            ))

            heroesManager.createHero()
            println("aaa")
            for(i in 1..heroesManager.pulHeroes.size){
                println(heroesManager.pulHeroes[i]?.rarity + " " + heroesManager.pulHeroes[i]?.ratingHero)
            }
        }
    }
}