package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.WindowManager


class ChiefActivity : AppCompatActivity() {
    private var dX = 0.0f
    private var dY = 0.0f
    private var startPositionX = 0.0f
    private var startPositionY = 0.0f
    private var downBorder = 0.0f

    private var heroesManager : HeroesManager? = null
    private var townManager = TownManager()
    private var activeHero : Heroes = Heroes()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_chief)

        setChefActivityButtonListener()
        setOnTouchImageListener()

        val workingBitmap = BitmapFactory.decodeResource(resources, R.drawable.hero_test)
        val mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true)
        heroesManager = HeroesManager(mutableBitmap)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
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
                                event.rawY - dY + v.height >= resourcesHeight -> resourcesHeight - v.height
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
                            v.x < startPositionX - resourcesWidth/7 -> {
                                println("left")
                                createNewHero()
                            }
                            v.x > startPositionX + resourcesWidth/7 -> {
                                println("right")
                            }
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

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val backToMainActivity = Intent(this, MainActivity2::class.java)
            startActivity(backToMainActivity)
        }
    }

    private fun createNewHero(){
        val imageView = findViewById<ImageView>(R.id.imageView2)
        val hero = heroesManager?.newHero()
        hero?.also { activeHero = it }
        imageView.setImageBitmap(hero?.imageHero)
    }

    // old ImageUpdate
    private fun setUpdateNewImage() {
        val imageView = findViewById<ImageView>(R.id.imageView2)

        val workingBitmap2 = BitmapFactory.decodeResource(resources, R.drawable.hero_test2)
        val mutableBitmap2 = workingBitmap2.copy(Bitmap.Config.ARGB_8888, true)
        val c = ImageHeroDrawable()
        imageView.setImageBitmap(c.create(mutableBitmap2, Heroes()))

//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.hero_test2);
//        imageView.setImageBitmap(bitmap)

    }
}