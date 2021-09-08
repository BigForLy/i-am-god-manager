package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.myapplication.Heroes
import com.example.myapplication.HeroesManager
import com.example.myapplication.R
import com.example.myapplication.TownManager
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var dX = 0.0f
    private var dY = 0.0f
    private var startPositionX = 0.0f
    private var startPositionY = 0.0f
    private var downBorder = 0.0f
    private var heroesManager : HeroesManager? = null
    private var townManager = TownManager()
    private var activeHero : Heroes = Heroes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val workingBitmap = BitmapFactory.decodeResource(resources, R.drawable.hero_test)
        val mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true)
        heroesManager = HeroesManager(mutableBitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)
        println(homeViewModel.text.value)
        println(homeViewModel.next)
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        setOnTouchImageListener(view.imageView)
        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchImageListener(imageView: ImageView) {

        imageView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val resourcesWidth = resources.displayMetrics.widthPixels
                val resourcesHeight = resources.displayMetrics.heightPixels - downBorder
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = event.rawX - v.x
                        dY = event.rawY - v.y
                        startPositionX = v.x
                        startPositionY = v.y
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
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
                    MotionEvent.ACTION_UP -> {
                        when {
                            v.x < startPositionX - resourcesWidth / 7 -> {
                                println("left")
                                createNewHero(imageView)
                            }
                            v.x > startPositionX + resourcesWidth / 7 -> {
                                println("right")
                            }
                            else -> {
                                println("none")
                            }
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

    private fun createNewHero(imageView: ImageView){
        val hero = heroesManager?.newHero()
        hero?.also { activeHero = it }
        imageView.setImageBitmap(hero?.imageHero)
    }
}