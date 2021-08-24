package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
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
    private var heroesManager = HeroesManager()
    private var townManager = TownManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                when (event.action) {
                    ACTION_DOWN -> {
                        dX = event.x
                        dY = event.y
                        startPositionX = v.x
                        startPositionY = v.y
                        return true
                    }
                    ACTION_MOVE -> {
                        val nowX : Float = if (v.x < 0) 0.0f else event.rawX - dX
                        println(v.x.toString() + " " + nowX)
                        when {
                            (event.rawX - dX >= 0.0f || v.x > 0.0f) && (event.rawX + (v.width - dX) <= resourcesWidth || (v.x + v.width) < resourcesWidth) -> {
                                v.animate()
                                    .x(event.rawX - dX)
                                    .y(event.rawY - dY)
                                    .setDuration(0)
                                    .start()
                            }
                            v.x < 0 -> {
                                v.animate()
                                    .x(0.0f)
                                    .y(event.rawY - dY)
                                    .setDuration(0)
                                    .start()
                            }
                            v.x + v.width > resourcesWidth -> {
                                v.animate()
                                    .x((resourcesWidth).toFloat()-v.width)
                                    .y(event.rawY - dY)
                                    .setDuration(0)
                                    .start()
                            }
                        }
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